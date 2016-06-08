package uk.co.donnellyit.jobtrak.main;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import uk.co.donnellyit.jobtrak.AppModule;
import uk.co.donnellyit.jobtrak.interactors.ListInteractor;

/**
 * Created by chrisdonnelly on 10/12/15.
 */
@Module(
        injects = ListFragment.class,
        addsTo = AppModule.class
)
public class ListModule {
    private ListView view;

    public ListModule(ListView view) {
        this.view = view;
    }

    @Provides
    @Singleton
    public ListView provideView() {
        return view;
    }

    @Provides @Singleton
    public ListPresenter providePresenter(ListView mainView, ListInteractor findItemsInteractor) {
        return new ListPresenterImpl(mainView, findItemsInteractor);
    }
}
