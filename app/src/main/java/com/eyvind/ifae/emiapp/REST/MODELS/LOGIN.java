package com.eyvind.ifae.emiapp.REST.MODELS;

/**
 * Created by Emilio-Emilio on 7/8/2015.
 */
public class LOGIN {
    boolean success;
    String message;
    User user;

    public String getMessage() {
        return message;
    }

    public boolean isSuccess() {
        return success;
    }

    public User getUser() {
        return user;
    }
}
