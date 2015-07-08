package com.eyvind.ifae.emiapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.activeandroid.ActiveAndroid;
import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.eyvind.ifae.emiapp.REGEX.VALIDATOR;
import com.eyvind.ifae.emiapp.REST.MODELS.LOGIN;
import com.eyvind.ifae.emiapp.REST.REST;
import com.eyvind.ifae.emiapp.REST.API;
import com.github.pwittchen.prefser.library.Prefser;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Header;
import retrofit.client.Response;

public class ActivityEMIapp extends Activity {
    private Button btnLogin;

    private EditText inEmail;
    private EditText inPassword;
    API service;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emiapp);

        final AwesomeValidation emailValidation = new AwesomeValidation(ValidationStyle.UNDERLABEL);
        final AwesomeValidation passwordValidation = new AwesomeValidation(ValidationStyle.UNDERLABEL);
        emailValidation.setContext(this);
        passwordValidation.setContext(this);

        btnLogin = (Button)findViewById(R.id.btnLogin);
        inEmail = (EditText)findViewById(R.id.inEmail);
        inPassword = (EditText)findViewById(R.id.inPassword);

        emailValidation.addValidation(inEmail, VALIDATOR.EMAIL, getString(R.string.validator_email));
        passwordValidation.addValidation(inPassword, VALIDATOR.PASSWORD, getString(R.string.validator_password));

        service = new REST(ActivityEMIapp.this).API().create(API.class);

        ActiveAndroid.initialize(this);

        if(getTokenRequest()!=null && !getTokenRequest().equals("")){
            login("", "", false);
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = inEmail.getText().toString();
                String password = inPassword.getText().toString();
                if(! emailValidation.validate() || !passwordValidation.validate()){
                    if(!emailValidation.validate()) YoYo.with(Techniques.Shake)
                            .duration(700)
                            .playOn(findViewById(R.id.inEmail));
                    if(!passwordValidation.validate()) YoYo.with(Techniques.Shake)
                                                        .duration(700)
                                                        .playOn(findViewById(R.id.inPassword));
                    return;
                }
                login(email,password,false);
            }
        });
    }

    public void saveTokenRequest(String token){
        Prefser pref = new Prefser(ActivityEMIapp.this);
        pref.put("TOKEN", token);
        Log.i("TOKEN", "-----------------------");
        Log.i("TOKEN",pref.get("TOKEN",String.class,""));
    }
    public String getTokenRequest(){
        Prefser pref = new Prefser(ActivityEMIapp.this);
        Log.i("TOKEN", "-----------------------");
        Log.i("TOKEN",pref.get("TOKEN",String.class,""));
        return pref.get("TOKEN",String.class,"");
    }
    public void login(String email,String password,final boolean AUTO_LOGIN){
        service.login(email, password, new Callback<LOGIN>() {
            @Override
            public void success(LOGIN login, Response response) {
                Toast.makeText(ActivityEMIapp.this,login.getMessage(),Toast.LENGTH_LONG).show();
                Log.i("LOGIN", response.getUrl());
                List<Header> lisHeader = response.getHeaders();
                 for(int i = 0 ; i<lisHeader.size();i++){
                     //Log.i("HEADERS",lisHeader.get(i).getName()+" >>Value: "+lisHeader.get(i).getValue());
                     if("Set-Cookie".equals(lisHeader.get(i).getName())){
                         if(lisHeader.get(i).getValue().contains("PHPSESSID")){
                             String value = lisHeader.get(i).getValue().split(";")[0].split("=")[1];
                             saveTokenRequest(value);
                         }
                     }
                 }
                if(login.isSuccess()){
                    Log.i("LOGIN", "----------------");
                    Log.i("LOGIN", login.getUser().getEmail());
                    /*table_me me = new table_me();
                    me.active = login.getUser().getActive();
                    me.email = login.getUser().getEmail();
                    me.id = login.getUser().getId();
                    me.name = login.getUser().getName();
                    me.remember_token = login.getUser().getRemember_token();
                    me.rules = login.getUser().getRules();
                    me.save();*/
                    startActivityMain();
                }else{
                    Toast.makeText(ActivityEMIapp.this,getString(R.string.error_internet_connection),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void failure(RetrofitError error) {
                Log.i("App", error!=null?error.getMessage():"unknown");
            }
        });
    }

    public void startActivityMain(){
        Intent principal = new Intent(ActivityEMIapp.this,MainActivity.class);
        startActivity(principal);
        finish();
    }
}
