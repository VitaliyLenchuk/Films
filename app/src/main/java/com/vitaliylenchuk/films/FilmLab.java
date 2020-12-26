package com.vitaliylenchuk.films;

import android.content.Context;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FilmLab {
    private static FilmLab sFilmLab;
    private List<Film> mFilms;
    private List<Film> mFavFilms;

    public static FilmLab get (Context context) {
        if (sFilmLab == null) {
            sFilmLab = new FilmLab(context);
        }
        return sFilmLab;
    }
    private FilmLab(Context context){
        mFilms = new ArrayList<>();
        FilmApp.getInstance().moviesService.getMovies().enqueue(new Callback<List<FilmJson>>() {
            @Override
            public void onResponse(Call<List<FilmJson>> call, Response<List<FilmJson>> response) {
                if (response.isSuccessful()){
                    List<FilmJson> filmJsonList = response.body();
                    mFilms.clear();
                    for (FilmJson filmJson : filmJsonList){
                        mFilms.add(new Film(filmJson));
                    }
                } else {
                    Toast.makeText(context, "FAILURE" + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<FilmJson>> call, Throwable t) {
                Toast.makeText(context, "FAILURE" +t.getClass().getSimpleName(), Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
        /*for (int i = 0; i<5; i++){
            Film film = new Film();
            film.setTitle("Film #" + i);
            film.setFilm_description("Description of Film #" + i);
            film.setFavourite(false);
            mFilms.add(film);
        }*/
    }

    public List<Film> getFilms(){
        return mFilms;
    }

    public List<Film> getFavFilms() {
        mFavFilms = new ArrayList<>();
        for (Film film : mFilms){
            if (film.isFavourite())
                mFavFilms.add(film);
        }
        return mFavFilms;
    }

    public Film getFilm(int id){
        for (Film film : mFilms){
            if (film.getId()==id){
                return film;
            }
        }
        return null;
    }

    public void addFilm (Film c){
        mFilms.add(c);
    }

}
