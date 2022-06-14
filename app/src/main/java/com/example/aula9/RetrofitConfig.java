package com.example.aula9;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RetrofitConfig {
    private final Retrofit retrofit;

    public RetrofitConfig() {
        retrofit = new Retrofit.Builder()
                .baseUrl("https://professor-allocation.herokuapp.com/")
                .addConverterFactory(JacksonConverterFactory.create())

                .build();
    }

    public CursoService criarService(){

        return retrofit.create(CursoService.class);
    }

}
