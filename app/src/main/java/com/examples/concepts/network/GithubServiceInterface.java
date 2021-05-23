package com.examples.concepts.network;

import com.examples.concepts.model.GithubModel;
import com.examples.concepts.model.GithubRepoModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GithubServiceInterface {

    @GET("{username}")
    Call<GithubModel> getGithubUserData(
            @Path("username") String userName
    );

    @GET("{username}/repos")
    Call<List<GithubRepoModel>> getGithubUserRepos(
            @Path("username") String userName
    );
}
