package com.examples.concepts.network;

import com.examples.concepts.model.MovieModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TMDBServiceInterface {

    @GET("{category}")
    Call<MovieModel> getPopularMovieList(
            @Path("category") String category,
            @Query("api_key") String api_key
    );
}
