package uk.co.donnellyit.jobtrak.interactors;

import uk.co.donnellyit.jobtrak.main.OnJobsFetchedListener;

/**
 * Created by chrisdonnelly on 15/06/2016.
 */

public interface JobFetchInteractor {
    void fetchJobs(OnJobsFetchedListener listener);
}
