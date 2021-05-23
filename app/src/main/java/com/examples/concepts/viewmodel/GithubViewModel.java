package com.examples.concepts.viewmodel;


import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.examples.concepts.model.GithubModel;
import com.examples.concepts.model.GithubRepoModel;
import com.examples.concepts.network.GithubRetrofitInstance;
import com.examples.concepts.network.GithubServiceInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GithubViewModel extends ViewModel {

    private final MutableLiveData<GithubModel> githubModelMutableLiveData;
    private final MutableLiveData<List<GithubRepoModel>> listMutableLiveData;

    public GithubViewModel() {
        githubModelMutableLiveData = new MutableLiveData<>();
        listMutableLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<GithubModel> getGithubModelMutableLiveData() {
        return githubModelMutableLiveData;
    }

    public MutableLiveData<List<GithubRepoModel>> getListMutableLiveData() {
        return listMutableLiveData;
    }

    public void userApiCalls(String userName){
        GithubServiceInterface githubServiceInterface = GithubRetrofitInstance.getRetrofitClient().create(GithubServiceInterface.class);
        Call<GithubModel> githubModelCall = githubServiceInterface.getGithubUserData(userName);
        githubModelCall.enqueue(new Callback<GithubModel>() {
            @Override
            public void onResponse(Call<GithubModel> call, Response<GithubModel> response) {
                githubModelMutableLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<GithubModel> call, Throwable t) {
                githubModelMutableLiveData.postValue(null);
            }
        });

        Call<List<GithubRepoModel>> listCall = githubServiceInterface.getGithubUserRepos(userName);
        listCall.enqueue(new Callback<List<GithubRepoModel>>() {
            @Override
            public void onResponse(Call<List<GithubRepoModel>> call, Response<List<GithubRepoModel>> response) {
                listMutableLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<List<GithubRepoModel>> call, Throwable t) {
                listMutableLiveData.postValue(null);
            }
        });
    }
}
