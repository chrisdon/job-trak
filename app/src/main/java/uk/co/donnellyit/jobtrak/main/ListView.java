package uk.co.donnellyit.jobtrak.main;

import java.util.List;

import uk.co.donnellyit.jobtrak.model.Event;
import uk.co.donnellyit.jobtrak.model.Job;

/**
 * Created by chrisdonnelly on 02/10/15.
 */
public interface ListView {
    void displayList(List<Job> jobs);
    void showProgress();
    void hideProgress();
    void onError(String msg);
}
