package eu.tinoba.androidarcitecturetemplate.ui.register;

import eu.tinoba.androidarcitecturetemplate.data.api.models.request.RegisterInformation;
import eu.tinoba.androidarcitecturetemplate.data.service.NetworkService;
import eu.tinoba.androidarcitecturetemplate.manager.StringManager;
import io.reactivex.Scheduler;
import timber.log.Timber;

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

    @Override
    public void register(final RegisterInformation registerInformation) {
        networkService.register(registerInformation)
                      .subscribeOn(subscribeScheduler)
                      .observeOn(observeScheduler)
                      .subscribe(o -> onRegisterSuccess(), throwable -> onRegisterFailure(throwable));
    }

    private void onRegisterFailure(final Throwable throwable) {
        Timber.e(throwable);
        view.goToLogin();
    }

    private void onRegisterSuccess() {
        view.goToLogin();
    }
}
