package uk.co.donnellyit.jobtrak.job;

import java.util.List;

import uk.co.donnellyit.jobtrak.model.Event;
import uk.co.donnellyit.jobtrak.model.Job;

/**
 * Created by chrisdonnelly on 20/12/15.
 */
public interface JobView {
    void hideProgress();
    void showProgress();
    void displayJob(Job job);
    void displayJobCreated(Job job);
    void displayList(List<Event> eventList);
    void onError(String msg);
}
