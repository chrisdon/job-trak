package uk.co.donnellyit.jobtrak.job;

import java.util.Map;

/**
 * Created by chrisdonnelly on 20/12/15.
 */
public interface JobPresenter {
    void updateJob(Map<String, Object> jobMap);
    void createJob();
    void fetchJob(String id);
}
