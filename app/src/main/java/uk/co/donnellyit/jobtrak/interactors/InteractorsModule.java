package uk.co.donnellyit.jobtrak.interactors;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by chrisdonnelly on 06/10/15.
 */
@Module(
        complete = false,
        library = true
)
public class InteractorsModule {
        @Provides
        @Singleton
        public ListInteractor provideListInteractor(Application app){
                return new ListInteractorRealmImpl(app);
        }

        @Provides
        @Singleton
        public JobFetchInteractor provideJobFetchInteractor(Application app){
                return new JobFetchInteractorImpl(app);
        }

        @Provides
        @Singleton
        public JobInteractor provideJobInteractor(Application app){
                return new JobInteractorImpl(app);
        }

        @Provides
        @Singleton
        public EventFetchInteractor provideEventInteractor(Application app){
                return new EventFetchInteractorRealmImpl(app);
        }
}
