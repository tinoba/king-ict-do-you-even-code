package eu.tinoba.androidarcitecturetemplate.injection.module;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import eu.tinoba.androidarcitecturetemplate.data.service.NetworkService;
import eu.tinoba.androidarcitecturetemplate.injection.scope.ForActivity;
import eu.tinoba.androidarcitecturetemplate.manager.StringManager;
import eu.tinoba.androidarcitecturetemplate.ui.details.DetailsPresenter;
import eu.tinoba.androidarcitecturetemplate.ui.details.DetailsPresenterImpl;
import eu.tinoba.androidarcitecturetemplate.ui.home.HomePresenter;
import eu.tinoba.androidarcitecturetemplate.ui.home.HomePresenterImpl;
import eu.tinoba.androidarcitecturetemplate.ui.login.LoginPresenter;
import eu.tinoba.androidarcitecturetemplate.ui.login.LoginPresenterImpl;
import eu.tinoba.androidarcitecturetemplate.ui.register.RegisterPresenter;
import eu.tinoba.androidarcitecturetemplate.ui.register.RegisterPresenterImpl;
import io.reactivex.Scheduler;

import static eu.tinoba.androidarcitecturetemplate.injection.module.ThreadingModule.OBSERVE_SCHEDULER;
import static eu.tinoba.androidarcitecturetemplate.injection.module.ThreadingModule.SUBSCRIBE_SCHEDULER;

@Module
public final class PresenterModule {

    @ForActivity
    @Provides
    HomePresenter provideHomePresenter(@Named(SUBSCRIBE_SCHEDULER) Scheduler subscribeScheduler, final NetworkService networkService,
                                       @Named(OBSERVE_SCHEDULER) Scheduler observeScheduler, StringManager stringManager) {
        return new HomePresenterImpl(subscribeScheduler, observeScheduler, stringManager, networkService);
    }

    @ForActivity
    @Provides
    LoginPresenter provideLoginPresenter(@Named(SUBSCRIBE_SCHEDULER) Scheduler subscribeScheduler, final NetworkService networkService,
                                        @Named(OBSERVE_SCHEDULER) Scheduler observeScheduler, StringManager stringManager) {
        return new LoginPresenterImpl(subscribeScheduler, observeScheduler, stringManager, networkService);
    }

    @ForActivity
    @Provides
    RegisterPresenter provideRegisterPresenter(@Named(SUBSCRIBE_SCHEDULER) Scheduler subscribeScheduler, final NetworkService networkService,
                                           @Named(OBSERVE_SCHEDULER) Scheduler observeScheduler, StringManager stringManager) {
        return new RegisterPresenterImpl(subscribeScheduler, observeScheduler, stringManager, networkService);
    }

    @ForActivity
    @Provides
    DetailsPresenter provideDetailsPresenter(@Named(SUBSCRIBE_SCHEDULER) Scheduler subscribeScheduler, final NetworkService networkService,
                                              @Named(OBSERVE_SCHEDULER) Scheduler observeScheduler, StringManager stringManager) {
        return new DetailsPresenterImpl(subscribeScheduler, observeScheduler, stringManager, networkService);
    }
}
