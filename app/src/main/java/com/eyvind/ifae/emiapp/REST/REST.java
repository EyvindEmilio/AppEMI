package com.eyvind.ifae.emiapp.REST;

import android.content.Context;

import com.github.pwittchen.prefser.library.Prefser;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.bind.DateTypeAdapter;

import java.util.Date;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

/**
 * Created by Emilio-Emilio on 7/7/2015.
 */
public class REST {
    Context context;
    public REST(Context context){
        this.context = context;
    }
    public RestAdapter restAdapter ;
    public Gson gson;
    public RestAdapter API(){
        gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .registerTypeAdapter(Date.class, new DateTypeAdapter())
                .create();

        return restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://192.168.1.3/baseAPI")
                .setConverter(new GsonConverter(gson))
                .setRequestInterceptor(new SessionRequestInterceptor(context))
                .build();
    }
}

class SessionRequestInterceptor implements RequestInterceptor{
    Context context;
    public SessionRequestInterceptor(Context context){
        this.context = context;
    }
    @Override
    public void intercept(RequestFacade request) {
        //table_me mSelect().from(test.class).where("name = ?", "Eyvind").execute();
        //List<table_me> m = new Select().from(table_me.class).limit(1).execute();
        Prefser pref = new Prefser(context);
        if(pref!=null && !pref.get("TOKEN",String.class,"").equals("") ){
            request.addHeader("Cookie", "PHPSESSID="+pref.get("TOKEN",String.class,"")+";");
        }
    }
}