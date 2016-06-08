package uk.co.donnellyit.jobtrak.event;

import java.util.Map;

import uk.co.donnellyit.jobtrak.model.Event;

/**
 * Created by chrisdonnelly on 20/12/15.
 */
public interface EventPresenter {
    void updateEvent(Map<String, Object> eventMap);
    void createEvent();
    void fetchEvent(String id);
}
