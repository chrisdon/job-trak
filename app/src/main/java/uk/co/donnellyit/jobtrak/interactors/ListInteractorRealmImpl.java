package uk.co.donnellyit.jobtrak.interactors;

import android.content.Context;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import uk.co.donnellyit.jobtrak.main.OnEventsFetchedListener;
import uk.co.donnellyit.jobtrak.model.Event;

/**
 * Created by chrisdonnelly on 27/05/2016.
 */

public class ListInteractorRealmImpl implements ListInteractor {

    private Context mContext;

    public ListInteractorRealmImpl(Context context) {
        mContext = context;
    }

    @Override
    public void fetchEvents(OnEventsFetchedListener listener) {
        // Create the Realm configuration
        RealmConfiguration realmConfig = new RealmConfiguration.Builder(mContext).build();
        // Open the Realm for the UI thread.
        Realm realm = Realm.getInstance(realmConfig);
        List<Event> events = realm.where(Event.class).findAll();
        listener.onEventsFinished(events);
    }
}
