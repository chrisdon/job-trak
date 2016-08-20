package uk.co.donnellyit.jobtrak.interactors;

import java.util.Map;

import uk.co.donnellyit.jobtrak.evententry.OnEventFetchedListener;

/**
 * Created by chrisdonnelly on 26/12/15.
 */
public interface EventFetchInteractor {
    void fetchEvent(String jobId, String eventId, OnEventFetchedListener listener);
    void createEvent(String jobId, OnEventFetchedListener listener);
    void updateEvent(String jobId, Map<String, Object> eventMap);
}
