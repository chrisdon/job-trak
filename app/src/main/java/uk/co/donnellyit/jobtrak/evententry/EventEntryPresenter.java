package uk.co.donnellyit.jobtrak.evententry;

import java.util.Map;

/**
 * Created by chrisdonnelly on 20/12/15.
 */
public interface EventEntryPresenter {
    void updateEvent(String jobId, Map<String, Object> eventMap);
    void createEvent(String jobId);
    void fetchEvent(String jobId, String eventId);
}
