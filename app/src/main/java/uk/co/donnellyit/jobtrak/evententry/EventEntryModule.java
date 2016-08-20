package uk.co.donnellyit.jobtrak.evententry;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import uk.co.donnellyit.jobtrak.AppModule;
import uk.co.donnellyit.jobtrak.interactors.EventFetchInteractor;
import uk.co.donnellyit.jobtrak.job.JobPresenter;
import uk.co.donnellyit.jobtrak.job.JobPresenterImpl;
import uk.co.donnellyit.jobtrak.job.JobView;

/**
 * Created by chrisdonnelly on 16/06/2016.
 */
@Module(
        injects = EventEntryFragment.class,
        addsTo = AppModule.class
)
public class EventEntryModule {
    private EventEntryView mView;

    public EventEntryModule(EventEntryView view) {
        mView = view;
    }

    @Provides
    @Singleton
    public EventEntryView provideView() {
        return mView;
    }

    @Provides @Singleton
    public EventEntryPresenter providePresenter(EventEntryView eventView, EventFetchInteractor eventInteractor) {
        return new EventEntryPresenterImpl(eventView, eventInteractor);
    }
}
