package com.android.service;

public class APIService {

    private static final String base_url="https://group2musicapp.000webhostapp.com/server/";

    public static Dataservice getService(){
        return APIRetrofitClient.getClient(base_url).create(Dataservice.class);
    }

}
