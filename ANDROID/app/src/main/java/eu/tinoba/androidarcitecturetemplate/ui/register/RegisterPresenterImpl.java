package eu.tinoba.androidarcitecturetemplate.ui.register;

import eu.tinoba.androidarcitecturetemplate.data.service.NetworkService;
import eu.tinoba.androidarcitecturetemplate.manager.StringManager;
import io.reactivex.Scheduler;

public final class RegisterPresenterImpl implements RegisterPresenter {

    private final Scheduler subscribeScheduler;
    private final Scheduler observeScheduler;
    private final StringManager stringManager;
    private final NetworkService networkService;

    private RegisterView view;

    public RegisterPresenterImpl(final Scheduler subscribeScheduler, final Scheduler observeScheduler, final StringManager stringManager,
                                 final NetworkService networkService) {
        this.subscribeScheduler = subscribeScheduler;
        this.observeScheduler = observeScheduler;
        this.stringManager = stringManager;
        this.networkService = networkService;
    }

    @Override
    public void setView(final RegisterView view) {
        this.view = view;
    }
}
