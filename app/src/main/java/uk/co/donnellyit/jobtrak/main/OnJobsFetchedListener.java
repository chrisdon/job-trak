package uk.co.donnellyit.jobtrak.main;

import java.util.List;

import uk.co.donnellyit.jobtrak.model.Event;
import uk.co.donnellyit.jobtrak.model.Job;

/**
 * Created by chrisdonnelly on 15/06/2016.
 */

public interface OnJobsFetchedListener {
    void onJobsFetched(List<Job> jobs);
    void onError(String msg);
}
