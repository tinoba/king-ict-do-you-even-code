package eu.tinoba.androidarcitecturetemplate.data.api.models.response;


import org.json.JSONObject;

public class LoginResponse {

    public final String Result;

    public LoginResponse(String result) {
        Result = result;
    }
}
