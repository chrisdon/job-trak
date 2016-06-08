package uk.co.donnellyit.jobtrak;

import android.app.Application;

import dagger.Module;
import dagger.Provides;
import uk.co.donnellyit.jobtrak.interactors.InteractorsModule;

/**
 * Created by chrisdonnelly on 06/10/15.
 */
@Module(
        injects = {
                JobApplication.class
        },
        includes = {
                InteractorsModule.class
        }
)
public class AppModule {
    private JobApplication app;

    public AppModule(JobApplication app) {
        this.app = app;
    }

    @Provides
    public Application provideApplication() {
        return app;
    }
}
