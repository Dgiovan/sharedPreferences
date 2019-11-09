package com.example.servicios.models;

import org.json.JSONObject;

public class itemejemplo1 {
    JSONObject objeto;

    public itemejemplo1(JSONObject objeto){
        this.objeto = objeto;
    }

    public JSONObject getItem(){ return objeto;}
}
