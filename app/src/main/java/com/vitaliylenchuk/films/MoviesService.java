package com.vitaliylenchuk.films;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MoviesService {
    @GET("movies")
    Call<List<FilmJson>> getMovies();
}
