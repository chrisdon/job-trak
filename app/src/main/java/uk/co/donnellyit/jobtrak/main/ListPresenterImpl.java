package uk.co.donnellyit.jobtrak.main;

import java.util.List;

import uk.co.donnellyit.jobtrak.interactors.ListInteractor;
import uk.co.donnellyit.jobtrak.model.Event;

/**
 * Created by chrisdonnelly on 10/12/15.
 */
public class ListPresenterImpl implements ListPresenter, OnEventsFetchedListener {

    private ListView mView;
    private ListInteractor mInteractor;

    public ListPresenterImpl(ListView view, ListInteractor interactor) {
        mView = view;
        mInteractor = interactor;
    }

    @Override
    public void fetchEventList() {
        mView.showProgress();
        mInteractor.fetchEvents(this);
    }

    @Override
    public void onEventsFinished(List<Event> events) {
        mView.displayList(events);
        mView.hideProgress();
    }

    @Override
    public void onError(String msg) {

    }
}
