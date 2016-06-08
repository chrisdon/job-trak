package uk.co.donnellyit.jobtrak.interactors;

import android.content.Context;

import com.couchbase.lite.CouchbaseLiteException;

import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import uk.co.donnellyit.jobtrak.database.StorageManager;
import uk.co.donnellyit.jobtrak.main.OnEventsFetchedListener;
import uk.co.donnellyit.jobtrak.model.Event;

/**
 * Created by chrisdonnelly on 10/12/15.
 */
public class ListInteractorImpl implements ListInteractor {

    private Context mContext;

    public ListInteractorImpl(Context context) {
        mContext = context;
    }

    @Override
    public void fetchEvents(final OnEventsFetchedListener listener) {
        StorageManager storageManager = new StorageManager(mContext);

        storageManager.eventsObservable().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Event>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onError(e.getLocalizedMessage());
                    }

                    @Override
                    public void onNext(List<Event> events) {
                        listener.onEventsFinished(events);
                    }
                });
    }
}
