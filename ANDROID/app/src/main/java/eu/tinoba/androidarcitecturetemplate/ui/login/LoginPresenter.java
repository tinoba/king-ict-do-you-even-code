package eu.tinoba.androidarcitecturetemplate.ui.login;

import eu.tinoba.androidarcitecturetemplate.data.api.models.request.UserInformation;

public interface LoginPresenter {

    void setView(LoginView view);

    void login(UserInformation userInformation);
}
