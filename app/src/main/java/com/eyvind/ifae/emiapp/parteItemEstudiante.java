package com.eyvind.ifae.emiapp;

/**
 * Created by Emilio-Emilio on 5/8/2015.
 */
public class parteItemEstudiante {

    String FullName;
    String Code;
    String Ci;
    public parteItemEstudiante(String ItemName,String Code,String Ci) {
        super();
        this.FullName = ItemName;
        this.Code = Code;
        this.Ci = Ci;
    }

    public String getCi() {
        return Ci;
    }

    public String getCode() {
        return Code;
    }

    public String getFullName() {
        return FullName;
    }

    public void setCi(String ci) {
        Ci = ci;
    }

    public void setCode(String code) {
        Code = code;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

}