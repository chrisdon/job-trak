package uk.co.donnellyit.jobtrak.interactors;

import android.content.Context;

import com.couchbase.lite.CouchbaseLiteException;

import java.util.Date;
import java.util.Map;

import uk.co.donnellyit.jobtrak.database.CrudHandler;
import uk.co.donnellyit.jobtrak.database.StorageManager;
import uk.co.donnellyit.jobtrak.event.OnEventFetchedListener;
import uk.co.donnellyit.jobtrak.model.Event;

/**
 * Created by chrisdonnelly on 26/12/15.
 */
public class EventFetchInteractorImpl implements EventFetchInteractor {

    private Context mContext;

    public EventFetchInteractorImpl(Context context) {
        mContext = context;
    }

    @Override
    public void fetchEvent(String eventId, OnEventFetchedListener listener) {
        StorageManager storageManager = new StorageManager(mContext);
        listener.onEventFetched(storageManager.fetchEvent(eventId));
    }

    @Override
    public void createEvent(OnEventFetchedListener listener) {
        StorageManager storageManager = new StorageManager(mContext);
        listener.onEventFetched(storageManager.createEvent());
    }

    @Override
    public void updateEvent(Map<String, Object> eventMap) {
        /*
        try {
            CrudHandler.update(event);
        } catch (CouchbaseLiteException e) {
            e.printStackTrace();
        }
        */
    }
}
