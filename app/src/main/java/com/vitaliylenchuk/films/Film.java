package com.vitaliylenchuk.films;

import android.media.Image;

import java.util.Random;
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

    public int getId() {
        return mId;
    }

    private int mId;
    private String mFilm_description;
    private int mPoster;

    public boolean isFavourite() {
        return isFavourite;
    }

    public void setFavourite(boolean favourite) {
        isFavourite = favourite;
    }

    private boolean isFavourite;

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    private String mTitle;

    public Film(){
        mId = new Random().nextInt(150);
        mPoster = R.drawable.no_photo;
    }

    public Film(FilmJson filmJson){
        mId = filmJson.id;
        mTitle = filmJson.title;
        mFilm_description = filmJson.description;
        mPoster = R.drawable.no_photo;
    }

}
