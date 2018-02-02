package eu.tinoba.androidarcitecturetemplate.data.service;

import eu.tinoba.androidarcitecturetemplate.data.api.models.request.UserInformation;
import eu.tinoba.androidarcitecturetemplate.data.api.models.response.LoginResponse;
import io.reactivex.Single;

public final class NetworkServiceImpl implements NetworkService {

    private final TemplateAPI templateAPI;

    public NetworkServiceImpl(final TemplateAPI templateAPI) {
        this.templateAPI = templateAPI;
    }

    @Override
    public Single<LoginResponse> login(final UserInformation userInformation) {
        return Single.defer(() -> templateAPI.login(userInformation));
    }

    @Override
    public Single<LoginResponse> getData(String id) {
        return Single.defer(() -> templateAPI.getData(id));
    }
}
