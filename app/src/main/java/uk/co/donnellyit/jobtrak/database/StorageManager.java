package uk.co.donnellyit.jobtrak.database;

import android.content.Context;
import android.util.Log;

import com.couchbase.lite.CouchbaseLiteException;
import com.couchbase.lite.Database;
import com.couchbase.lite.Document;
import com.couchbase.lite.Emitter;
import com.couchbase.lite.Manager;
import com.couchbase.lite.Mapper;
import com.couchbase.lite.Query;
import com.couchbase.lite.QueryEnumerator;
import com.couchbase.lite.QueryRow;
import com.couchbase.lite.View;
import com.couchbase.lite.android.AndroidContext;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import rx.Observable;
import rx.functions.Func0;
import uk.co.donnellyit.jobtrak.model.Event;

/**
 * Created by chrisdonnelly on 26/11/15.
 */
public class StorageManager {
    private final static String DB_NAME_EVENT = "db_event";
    private static final String TAG = "StorageManager";
    private final static String EVENT_VIEW = "event_view";
    private final static String JOB_VIEW = "job_view";
    private final static String PERSON_VIEW = "person_view";
    private final static String COMPANY_VIEW = "company_view";

    private Context mContext;
    private Manager mManager;
    private Database mEventDatabase;

    public StorageManager(Context context) {
        mContext = context;

        openDb();
        registerViews();
    }

    private void openDb() {
        if (!Manager.isValidDatabaseName(DB_NAME_EVENT)) {
            Log.e(TAG, "Bad database name");
            return;
        }

        try {
            mManager = new Manager(new AndroidContext(mContext), Manager.DEFAULT_OPTIONS);

            mEventDatabase = mManager.getDatabase(DB_NAME_EVENT);
        } catch (IOException e) {
            Log.e(TAG, "Cannot create manager object");
            return;
        } catch (CouchbaseLiteException e) {
            e.printStackTrace();
        }
    }

    public Database getEventDb() {
        return mEventDatabase;
    }

    private void registerViews() {
        View eventView = mEventDatabase.getView(EVENT_VIEW);
        eventView.setMap(new Mapper() {

            @Override
            public void map(Map<String, Object> document, Emitter emitter) {
                emitter.emit(document.get(Event.COLUMN_ID), document.get(Event.COLUMN_DATE));
            }
        }, "1");
    }

    /**
     * Fetch events from the db
     *
     * @return Events dear boy, Events
     * @throws CouchbaseLiteException
     */
    private List<Event> fetchEvents() {
        View eventView = mEventDatabase.getView(EVENT_VIEW);
        Query eventQuery = eventView.createQuery();
        eventQuery.setDescending(true);
        QueryEnumerator results = null;
        try {
            results = eventQuery.run();
        } catch (CouchbaseLiteException e) {
            e.printStackTrace();
        }

        return buildEventList(results);
    }

    public Observable<List<Event>> eventsObservable() {
        return Observable.defer(new Func0<Observable<List<Event>>>() {

            @Override
            public Observable<List<Event>> call() {
                return Observable.just(fetchEvents());
            }
        });
    }

    public View getEventView() {
        return mEventDatabase.getView(EVENT_VIEW);
    }

    public List<Event> buildEventList(QueryEnumerator results) {
        List<Event> events = new ArrayList<>();
        /* Iterate through the rows to get the documents */
        for (Iterator<QueryRow> it = results; it.hasNext();) {
            QueryRow row = it.next();
            //String docId = (String) row.getKey();
            Event event = new Event(row.getDocument());
            events.add(event);
        }

        Collections.sort(events);

        return events;
    }


    /**
     * Fetch event from the db
     *
     * @param eventId
     * @return
     */
    public Event fetchEvent(String eventId) {
        Document doc = CrudHandler.read(mEventDatabase, eventId);
        return new Event(doc);
    }

    /**
     * Create an event
     *
     * @return newly created @link Event
     * @throws CouchbaseLiteException
     */
    public Event createEvent() {
        Map<String, Object> map = new HashMap<>();
        map.put(Event.COLUMN_DATE, Calendar.getInstance().getTimeInMillis());
        Document doc = null;
        try {
            doc = CrudHandler.create(mEventDatabase, map);
        } catch (CouchbaseLiteException e) {
            e.printStackTrace();
            return null;
        }
        return new Event(doc);
    }

}
