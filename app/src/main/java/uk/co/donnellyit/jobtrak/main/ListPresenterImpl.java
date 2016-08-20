package uk.co.donnellyit.jobtrak.main;

import java.util.List;

import uk.co.donnellyit.jobtrak.interactors.JobFetchInteractor;
import uk.co.donnellyit.jobtrak.interactors.ListInteractor;
import uk.co.donnellyit.jobtrak.model.Event;
import uk.co.donnellyit.jobtrak.model.Job;

/**
 * Created by chrisdonnelly on 10/12/15.
 */
public class ListPresenterImpl implements ListPresenter, OnJobsFetchedListener {

    private ListView mView;
    private JobFetchInteractor mInteractor;

    public ListPresenterImpl(ListView view, JobFetchInteractor interactor) {
        mView = view;
        mInteractor = interactor;
    }

    @Override
    public void fetchJobList() {
        mView.showProgress();
        mInteractor.fetchJobs(this);
    }

    @Override
    public void onJobsFetched(List<Job> jobs) {
        mView.hideProgress();
        mView.displayList(jobs);
    }

    @Override
    public void onError(String msg) {

    }
}
