package com.examples.concepts.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.examples.concepts.model.MovieModel;
import com.examples.concepts.network.TMDBServiceInterface;
import com.examples.concepts.network.RetrofitInstance;
import com.examples.concepts.utils.Constants;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieViewModel extends ViewModel {

    private final MutableLiveData<List<MovieModel.Results>> modelMutableLiveData;

    public MovieViewModel() {
        modelMutableLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<List<MovieModel.Results>> getModelMutableLiveDataObserver(){
        return modelMutableLiveData;
    }

    public void apiCall(String movieType){
        TMDBServiceInterface apiServiceInterface = RetrofitInstance.getRetrofitClient().create(TMDBServiceInterface.class);
        Call<MovieModel> modelCall = apiServiceInterface.getPopularMovieList(movieType, Constants.TMDB_API_KEY);
        modelCall.enqueue(new Callback<MovieModel>() {
            @Override
            public void onResponse(@NotNull Call<MovieModel> call, @NotNull Response<MovieModel> response) {
                assert response.body() != null;
                List<MovieModel.Results> resultsList = response.body().getResults();
                modelMutableLiveData.postValue(resultsList);
            }

            @Override
            public void onFailure(@NotNull Call<MovieModel> call, @NotNull Throwable t) {
                modelMutableLiveData.postValue(null);
            }
        });
    }
}
