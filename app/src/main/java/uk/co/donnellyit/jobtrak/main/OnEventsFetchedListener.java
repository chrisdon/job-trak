package uk.co.donnellyit.jobtrak.main;

import java.util.List;

import uk.co.donnellyit.jobtrak.model.Event;

/**
 * Created by chrisdonnelly on 10/12/15.
 */
public interface OnEventsFetchedListener {
    void onEventsFinished(List<Event> events);
    void onError(String msg);
}
