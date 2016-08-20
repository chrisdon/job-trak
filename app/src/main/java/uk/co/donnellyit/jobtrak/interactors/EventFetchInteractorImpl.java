package uk.co.donnellyit.jobtrak.interactors;

import android.content.Context;

import java.util.Map;

import uk.co.donnellyit.jobtrak.database.StorageManager;
import uk.co.donnellyit.jobtrak.evententry.OnEventFetchedListener;

/**
 * Created by chrisdonnelly on 26/12/15.
 */
public class EventFetchInteractorImpl implements EventFetchInteractor {

    private Context mContext;

    public EventFetchInteractorImpl(Context context) {
        mContext = context;
    }

    @Override
    public void fetchEvent(String jobId, String eventId, OnEventFetchedListener listener) {
        StorageManager storageManager = new StorageManager(mContext);
        listener.onEventFetched(storageManager.fetchEvent(eventId));
    }

    @Override
    public void createEvent(String jobId, OnEventFetchedListener listener) {
        StorageManager storageManager = new StorageManager(mContext);
        listener.onEventFetched(storageManager.createEvent());
    }

    @Override
    public void updateEvent(String jobId, Map<String, Object> eventMap) {
        /*
        try {
            CrudHandler.update(event);
        } catch (CouchbaseLiteException e) {
            e.printStackTrace();
        }
        */
    }
}
