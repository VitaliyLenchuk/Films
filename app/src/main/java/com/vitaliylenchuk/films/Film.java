package com.vitaliylenchuk.films;

import android.media.Image;

import java.util.UUID;

public class Film {
    public String getFilm_description() {
        return mFilm_description;
    }

    public void setFilm_description(String film_description) {
        mFilm_description = film_description;
    }

    public int getPoster() {
        return mPoster;
    }

    public void setPoster(int poster) {
        mPoster = poster;
    }

    public UUID getId() {
        return mId;
    }

    private UUID mId;
    private String mFilm_description;
    private int mPoster;

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    private String mTitle;

    public Film(){
        mId = UUID.randomUUID();
        mPoster = R.drawable.no_photo;
    }

}
