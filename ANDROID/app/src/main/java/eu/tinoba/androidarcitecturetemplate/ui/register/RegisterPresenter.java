package eu.tinoba.androidarcitecturetemplate.ui.register;

import eu.tinoba.androidarcitecturetemplate.data.api.models.request.RegisterInformation;

public interface RegisterPresenter {

    void setView(RegisterView view);

    void register(RegisterInformation registerInformation);
}
