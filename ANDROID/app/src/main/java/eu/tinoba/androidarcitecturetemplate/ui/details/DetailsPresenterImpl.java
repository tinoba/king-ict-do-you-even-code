package eu.tinoba.androidarcitecturetemplate.ui.details;

import org.json.JSONException;

import eu.tinoba.androidarcitecturetemplate.data.api.models.request.UserInformation;
import eu.tinoba.androidarcitecturetemplate.data.api.models.response.LoginResponse;
import eu.tinoba.androidarcitecturetemplate.data.service.NetworkService;
import eu.tinoba.androidarcitecturetemplate.manager.StringManager;
import io.reactivex.Scheduler;
import timber.log.Timber;

public final class DetailsPresenterImpl implements DetailsPresenter {

    private final Scheduler subscribeScheduler;
    private final Scheduler observeScheduler;
    private final StringManager stringManager;
    private final NetworkService networkService;

    private DetailsView view;

    public DetailsPresenterImpl(final Scheduler subscribeScheduler, final Scheduler observeScheduler, final StringManager stringManager,
                                final NetworkService networkService) {
        this.subscribeScheduler = subscribeScheduler;
        this.observeScheduler = observeScheduler;
        this.stringManager = stringManager;
        this.networkService = networkService;
    }

    @Override
    public void setView(final DetailsView view) {
        this.view = view;
    }

    @Override
    public void getData(String id) {
        networkService.getData(id)
                .subscribeOn(subscribeScheduler)
                .observeOn(observeScheduler)
                .subscribe(loginResponse -> onGetDataSuccess(loginResponse), throwable -> onGetDataFailure(throwable));
    }

    private void onGetDataFailure(Throwable throwable) {
        Timber.e(throwable.getMessage());
    }

    private void onGetDataSuccess(LoginResponse loginResponse) throws JSONException {
        view.showDetailsData(loginResponse);
    }
}
