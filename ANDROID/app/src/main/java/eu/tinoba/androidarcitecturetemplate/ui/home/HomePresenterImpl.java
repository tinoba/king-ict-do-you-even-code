package eu.tinoba.androidarcitecturetemplate.ui.home;

import eu.tinoba.androidarcitecturetemplate.data.service.NetworkService;
import eu.tinoba.androidarcitecturetemplate.manager.StringManager;
import io.reactivex.Scheduler;

public final class HomePresenterImpl implements HomePresenter {

    private final Scheduler subscribeScheduler;
    private final Scheduler observeScheduler;
    private final StringManager stringManager;
    private final NetworkService networkService;

    private HomeView view;

    public HomePresenterImpl(final Scheduler subscribeScheduler, final Scheduler observeScheduler, final StringManager stringManager,
                             final NetworkService networkService) {
        this.subscribeScheduler = subscribeScheduler;
        this.observeScheduler = observeScheduler;
        this.stringManager = stringManager;
        this.networkService = networkService;
    }

    @Override
    public void setView(final HomeView view) {
        this.view = view;
    }
}
