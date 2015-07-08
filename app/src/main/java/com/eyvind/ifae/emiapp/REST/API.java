package com.eyvind.ifae.emiapp.REST;

import com.eyvind.ifae.emiapp.REST.MODELS.LOGIN;
import com.eyvind.ifae.emiapp.REST.MODELS.LOGOUT;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by Emilio-Emilio on 7/7/2015.
 */
public interface API {
    @GET("/login")
    void login(@Query("email") String email, @Query("password") String password, Callback<LOGIN> callback);
    @GET("/logout")
    void logout(Callback<LOGOUT> callback);
}
