package com.vitaliylenchuk.films;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FilmLab {
    private static FilmLab sFilmLab;
    private List<Film> mFilms;

    public static FilmLab get (Context context) {
        if (sFilmLab == null) {
            sFilmLab = new FilmLab(context);
        }
        return sFilmLab;
    }
    private FilmLab(Context context){
        mFilms = new ArrayList<>();
        for (int i = 0; i<5; i++){
            Film film = new Film();
            film.setTitle("Film #" + i);
            film.setFilm_description("Description of Film #" + i);
            mFilms.add(film);
        }
    }

    public List<Film> getFilms(){
        return mFilms;
    }

    public Film getFilm(UUID id){
        for (Film film : mFilms){
            if (film.getId().equals(id)){
                return film;
            }
        }
        return null;
    }

    public void addFilm (Film c){
        mFilms.add(c);
    }

}
