package uk.co.donnellyit.jobtrak.evententry;

import uk.co.donnellyit.jobtrak.model.Event;

/**
 * Created by chrisdonnelly on 15/06/2016.
 */

public interface EventEntryView {
    void hideProgress();
    void showProgress();
    void displayEvent(Event event);
    void onError(String msg);
}
