package uk.co.donnellyit.jobtrak.interactors;

import android.content.Context;


import java.util.List;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import uk.co.donnellyit.jobtrak.main.OnJobsFetchedListener;
import uk.co.donnellyit.jobtrak.model.Job;

/**
 * Created by chrisdonnelly on 15/06/2016.
 */

public class JobFetchInteractorImpl implements JobFetchInteractor {

    private Context mContext;
    private OnJobsFetchedListener mListener;

    private RealmChangeListener callback = new RealmChangeListener() {
        @Override
        public void onChange(Object element) {
            RealmResults<Job> jobs = (RealmResults<Job>) element;
            mListener.onJobsFetched(jobs);
        }
    };

    public JobFetchInteractorImpl(Context context) {
        mContext = context;
    }
    @Override
    public void fetchJobs(OnJobsFetchedListener listener) {
        mListener = listener;
        RealmConfiguration realmConfig = new RealmConfiguration.Builder(mContext).build();
        Realm realm = Realm.getInstance(realmConfig);

        RealmResults<Job> realmJobs = realm.where(Job.class).findAll();
        List<Job> jobs = realm.copyFromRealm(realmJobs);
        mListener.onJobsFetched(jobs);

        //TODO Run query in non UI Thread
        //RealmResults<Job> jobs = realm.where(Job.class).findAllAsync();
        //jobs.addChangeListener(callback);
        /*Observable<RealmResults<Job>> observable = realm.where(Job.class).findAllAsync().asObservable();
        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RealmResults<Job>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mListener.onError(e.getLocalizedMessage());
                    }

                    @Override
                    public void onNext(RealmResults<Job> jobs) {
                        mListener.onJobsFetched(jobs);
                    }
                });*/

        realm.close();
    }
}
