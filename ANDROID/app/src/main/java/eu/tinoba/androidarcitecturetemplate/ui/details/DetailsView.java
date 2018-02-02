package eu.tinoba.androidarcitecturetemplate.ui.details;

import org.json.JSONException;

import eu.tinoba.androidarcitecturetemplate.data.api.models.response.LoginResponse;

public interface DetailsView {

    void showDetailsData(LoginResponse loginResponse) throws JSONException;

    void failed();
}
