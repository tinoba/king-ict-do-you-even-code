package eu.tinoba.androidarcitecturetemplate.data.service;

import eu.tinoba.androidarcitecturetemplate.data.api.models.request.RegisterInformation;
import eu.tinoba.androidarcitecturetemplate.data.api.models.request.UserInformation;
import eu.tinoba.androidarcitecturetemplate.data.api.models.response.LoginResponse;
import io.reactivex.Single;

public interface NetworkService {

    Single<LoginResponse> login(UserInformation userInformation);

    Single<LoginResponse> getData(String id);

    Single<Object> register(RegisterInformation registerInformation);
}
