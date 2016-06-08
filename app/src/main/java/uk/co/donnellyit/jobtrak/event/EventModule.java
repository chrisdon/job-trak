package uk.co.donnellyit.jobtrak.event;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import uk.co.donnellyit.jobtrak.AppModule;
import uk.co.donnellyit.jobtrak.interactors.EventFetchInteractor;

/**
 * Created by chrisdonnelly on 26/12/15.
 */
@Module(
        injects = EventFragment.class,
        addsTo = AppModule.class
)
public class EventModule {
    private EventView mView;

    public EventModule(EventView view) {
        mView = view;
    }

    @Provides
    @Singleton
    public EventView provideView() {
        return mView;
    }

    @Provides @Singleton
    public EventPresenter providePresenter(EventView eventView, EventFetchInteractor eventInteractor) {
        return new EventPresenterImpl(eventView, eventInteractor);
    }
}
