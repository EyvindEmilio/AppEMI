package com.eyvind.ifae.emiapp.REST.MODELS;

/**
 * Created by Emilio-Emilio on 7/7/2015.
 */
public class LOGIN_T {
    String name;
    String last_name;
    String concat;

    public String getConcat() {
        return concat!=null?concat:"";
    }

    public void setConcat(String concat) {
        this.concat = concat;
    }

    public String getLast_name() {
        return last_name!=null?last_name:"";
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getName() {
        return name!=null?name:"";
    }

    public void setName(String name) {
        this.name = name;
    }
}
