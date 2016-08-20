package uk.co.donnellyit.jobtrak.job;

import java.util.Map;

import uk.co.donnellyit.jobtrak.interactors.JobFetchInteractor;
import uk.co.donnellyit.jobtrak.interactors.JobInteractor;
import uk.co.donnellyit.jobtrak.model.Event;
import uk.co.donnellyit.jobtrak.model.Job;

/**
 * Created by chrisdonnelly on 26/12/15.
 */
public class JobPresenterImpl implements JobPresenter, OnJobFetchedListener {

    private JobView mView;
    private JobInteractor mInteractor;

    public JobPresenterImpl(JobView view, JobInteractor interactor) {
        mView = view;
        mInteractor = interactor;
    }

    @Override
    public void onJobFetched(Job job) {
        mView.hideProgress();
        mView.displayJob(job);
    }

    @Override
    public void onJobCreated(Job job) {
        mView.hideProgress();
        mView.displayJobCreated(job);
    }

    @Override
    public void onError(String msg) {
        mView.onError(msg);
    }

    @Override
    public void updateJob(Map<String, Object> jobMap) {
        mInteractor.updateJob(jobMap);
    }

    @Override
    public void createJob() {
        mInteractor.createJob(this);
    }

    @Override
    public void fetchJob(String id) {
        mInteractor.fetchJob(id, this);
    }
}
