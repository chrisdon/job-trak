package uk.co.donnellyit.jobtrak.job;

import uk.co.donnellyit.jobtrak.model.Job;

/**
 * Created by chrisdonnelly on 16/06/2016.
 */

public interface OnJobFetchedListener {
    void onJobFetched(Job job);
    void onJobCreated(Job job);
    void onError(String msg);
}
