package com.vitaliylenchuk.films;

import android.app.Application;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FilmApp extends Application {
    public MoviesService moviesService;
    private static FilmApp instance;
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        initRetrofit();
    }

    public static FilmApp getInstance(){
        return instance;
    }

    private void initRetrofit(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://my-json-server.typicode.com/VitaliyLenchuk/Movies_base/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        moviesService = retrofit.create(MoviesService.class);
    }
}
