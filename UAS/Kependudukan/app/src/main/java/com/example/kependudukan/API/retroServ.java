package com.example.kependudukan.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class retroServ {
    private static final String baseURL = "http://10.0.2.2/kependudukan/";
    private static Retrofit retro;

    public static Retrofit konekRetro(){
        if (retro==null){
            retro = new Retrofit.Builder()
                            .baseUrl(baseURL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
        }
        return retro;
    }
}
