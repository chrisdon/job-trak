package uk.co.donnellyit.jobtrak.interactors;

import java.util.Map;

import uk.co.donnellyit.jobtrak.job.OnJobFetchedListener;

/**
 * Created by chrisdonnelly on 26/12/15.
 */
public interface JobInteractor {
    void fetchJob(String id, OnJobFetchedListener listener);
    void createJob(OnJobFetchedListener listener);
    void updateJob(Map<String, Object> jobMap);
}
