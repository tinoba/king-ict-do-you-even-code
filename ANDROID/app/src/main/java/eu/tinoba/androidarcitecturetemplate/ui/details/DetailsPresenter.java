package eu.tinoba.androidarcitecturetemplate.ui.details;

import eu.tinoba.androidarcitecturetemplate.data.api.models.request.UserInformation;

public interface DetailsPresenter {

    void setView(DetailsView view);

    void getData(String id);
}
