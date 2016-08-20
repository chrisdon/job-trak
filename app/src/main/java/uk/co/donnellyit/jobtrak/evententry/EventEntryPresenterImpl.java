package uk.co.donnellyit.jobtrak.evententry;

import java.util.Map;

import uk.co.donnellyit.jobtrak.interactors.EventFetchInteractor;
import uk.co.donnellyit.jobtrak.model.Event;

/**
 * Created by chrisdonnelly on 26/12/15.
 */
public class EventEntryPresenterImpl implements EventEntryPresenter, OnEventFetchedListener {

    private EventEntryView mView;
    private EventFetchInteractor mInteractor;

    public EventEntryPresenterImpl(EventEntryView view, EventFetchInteractor interactor) {
        mView = view;
        mInteractor = interactor;
    }

    @Override
    public void updateEvent(String jobId, Map<String, Object> eventMap) {
        mInteractor.updateEvent(jobId, eventMap);
    }

    @Override
    public void createEvent(String jobId) {
        mInteractor.createEvent(jobId, this);
    }

    @Override
    public void fetchEvent(String jobId, String eventId) {
        mInteractor.fetchEvent(jobId, eventId, this);
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
