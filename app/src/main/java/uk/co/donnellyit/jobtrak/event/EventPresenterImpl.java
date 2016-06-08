package uk.co.donnellyit.jobtrak.event;

import java.util.Map;

import uk.co.donnellyit.jobtrak.interactors.EventFetchInteractor;
import uk.co.donnellyit.jobtrak.model.Event;

/**
 * Created by chrisdonnelly on 26/12/15.
 */
public class EventPresenterImpl implements EventPresenter, OnEventFetchedListener {

    private EventView mView;
    private EventFetchInteractor mInteractor;

    public EventPresenterImpl(EventView view, EventFetchInteractor interactor) {
        mView = view;
        mInteractor = interactor;
    }

    @Override
    public void updateEvent(Map<String, Object> eventMap) {
        mInteractor.updateEvent(eventMap);
    }

    @Override
    public void createEvent() {
        mInteractor.createEvent(this);
    }

    @Override
    public void fetchEvent(String id) {
        mInteractor.fetchEvent(id, this);
    }

    @Override
    public void onEventFetched(Event event) {
        mView.displayEvent(event);
    }

    @Override
    public void onError(String msg) {
        mView.onError(msg);
    }
}
