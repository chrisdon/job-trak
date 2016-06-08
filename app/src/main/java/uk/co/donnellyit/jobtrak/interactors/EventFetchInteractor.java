package uk.co.donnellyit.jobtrak.interactors;

import java.util.Date;
import java.util.Map;

import uk.co.donnellyit.jobtrak.event.OnEventFetchedListener;
import uk.co.donnellyit.jobtrak.model.Event;

/**
 * Created by chrisdonnelly on 26/12/15.
 */
public interface EventFetchInteractor {
    void fetchEvent(String id, OnEventFetchedListener listener);
    void createEvent(OnEventFetchedListener listener);
    void updateEvent(Map<String, Object> eventMap);
}
