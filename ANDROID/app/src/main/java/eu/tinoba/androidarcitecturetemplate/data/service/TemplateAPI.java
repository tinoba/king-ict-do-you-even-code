package eu.tinoba.androidarcitecturetemplate.data.service;

import eu.tinoba.androidarcitecturetemplate.data.api.models.request.RegisterInformation;
import eu.tinoba.androidarcitecturetemplate.data.api.models.request.UserInformation;
import eu.tinoba.androidarcitecturetemplate.data.api.models.response.LoginResponse;
import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface TemplateAPI {

    @POST("change/api/hr/account/login")
    Single<LoginResponse> login(@Body UserInformation userInformation);

    @POST("change/api/hr/account/register")
    Single<Object> register(@Body RegisterInformation registerInformation);

    @Headers("X-Authorization: application/x-www-form-urlencoded")
    @GET("change/api/hr/team/details/{id}")
    Single<LoginResponse> getData(@Path("id") String id);
/*
    @GET(PATH_HOTEL)
    Single<List<Hotel>> getHotel(@Path("id") long id);*/
}
