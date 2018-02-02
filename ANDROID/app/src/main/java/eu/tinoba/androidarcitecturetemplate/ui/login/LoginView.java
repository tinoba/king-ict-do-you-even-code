package eu.tinoba.androidarcitecturetemplate.ui.login;

import org.json.JSONException;

import eu.tinoba.androidarcitecturetemplate.data.api.models.response.LoginResponse;

public interface LoginView {

    void goToNextScreen(LoginResponse loginResponse) throws JSONException;
    void failed() ;
}
