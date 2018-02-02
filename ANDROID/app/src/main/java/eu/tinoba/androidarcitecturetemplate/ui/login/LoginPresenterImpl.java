package eu.tinoba.androidarcitecturetemplate.ui.login;

import android.util.Log;

import org.json.JSONException;

import eu.tinoba.androidarcitecturetemplate.data.api.models.request.UserInformation;
import eu.tinoba.androidarcitecturetemplate.data.api.models.response.LoginResponse;
import eu.tinoba.androidarcitecturetemplate.data.service.NetworkService;
import eu.tinoba.androidarcitecturetemplate.manager.StringManager;
import io.reactivex.Scheduler;
import timber.log.Timber;

public final class LoginPresenterImpl implements LoginPresenter {

    private final Scheduler subscribeScheduler;
    private final Scheduler observeScheduler;
    private final StringManager stringManager;
    private final NetworkService networkService;

    private LoginView view;

    public LoginPresenterImpl(final Scheduler subscribeScheduler, final Scheduler observeScheduler, final StringManager stringManager,
                              final NetworkService networkService) {
        this.subscribeScheduler = subscribeScheduler;
        this.observeScheduler = observeScheduler;
        this.stringManager = stringManager;
        this.networkService = networkService;
    }

    @Override
    public void setView(final LoginView view) {
        this.view = view;
    }

    @Override
    public void login(UserInformation userInformation) {
        networkService.login(userInformation)
                .subscribeOn(subscribeScheduler)
                .observeOn(observeScheduler)
                .subscribe(loginResponse -> onLoginSuccess(loginResponse), throwable -> onLoginFailure(throwable));
    }

    private void onLoginFailure(Throwable throwable) {
        Timber.e(throwable.getMessage());
        view.failed();
    }

    private void onLoginSuccess(LoginResponse loginResponse) throws JSONException {
        view.goToNextScreen(loginResponse);
    }
}
