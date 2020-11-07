package com.vitaliylenchuk.films;

import android.media.Image;

public class Film {
    public int getFilm_description() {
        return mFilm_description;
    }

    public void setFilm_description(int film_description) {
        mFilm_description = film_description;
    }

    public int getPoster() {
        return mPoster;
    }

    public void setPoster(int poster) {
        mPoster = poster;
    }

    private int mFilm_description;
    private int mPoster;

    public Film(int film_description, int poster){
        mFilm_description = film_description;
        mPoster = poster;
    }
}
