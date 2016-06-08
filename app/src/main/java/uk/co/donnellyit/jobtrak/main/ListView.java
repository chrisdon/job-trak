package uk.co.donnellyit.jobtrak.main;

import java.util.List;

import uk.co.donnellyit.jobtrak.model.Event;

/**
 * Created by chrisdonnelly on 02/10/15.
 */
public interface ListView {
    void displayList(List<Event> eventList);
    void showProgress();
    void hideProgress();
    void onError();
}
