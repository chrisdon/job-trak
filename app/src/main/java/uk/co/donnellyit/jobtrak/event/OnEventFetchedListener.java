package uk.co.donnellyit.jobtrak.event;

import uk.co.donnellyit.jobtrak.model.Event;

/**
 * Created by chrisdonnelly on 21/12/15.
 */
public interface OnEventFetchedListener {
    void onEventFetched(Event event);
    void onError(String msg);
}
