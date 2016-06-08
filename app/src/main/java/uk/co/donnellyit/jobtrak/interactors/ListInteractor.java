package uk.co.donnellyit.jobtrak.interactors;

import uk.co.donnellyit.jobtrak.main.OnEventsFetchedListener;

/**
 * Created by chrisdonnelly on 10/12/15.
 */
public interface ListInteractor {
    void fetchEvents(OnEventsFetchedListener listener);
}
