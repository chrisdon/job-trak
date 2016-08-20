package uk.co.donnellyit.jobtrak.job;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import uk.co.donnellyit.jobtrak.AppModule;
import uk.co.donnellyit.jobtrak.interactors.EventFetchInteractor;
import uk.co.donnellyit.jobtrak.interactors.JobInteractor;

/**
 * Created by chrisdonnelly on 26/12/15.
 */
@Module(
        injects = JobFragment.class,
        addsTo = AppModule.class
)
public class JobModule {
    private JobView mView;

    public JobModule(JobView view) {
        mView = view;
    }

    @Provides
    @Singleton
    public JobView provideView() {
        return mView;
    }

    @Provides @Singleton
    public JobPresenter providePresenter(JobView jobView, JobInteractor jobInteractor) {
        return new JobPresenterImpl(jobView, jobInteractor);
    }
}
