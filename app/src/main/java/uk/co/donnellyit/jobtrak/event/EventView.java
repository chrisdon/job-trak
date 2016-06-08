package uk.co.donnellyit.jobtrak.event;

import uk.co.donnellyit.jobtrak.model.Event;

/**
 * Created by chrisdonnelly on 20/12/15.
 */
public interface EventView {
    void hideProgress();
    void showProgress();
    void displayEvent(Event event);
    void onError(String msg);
}
