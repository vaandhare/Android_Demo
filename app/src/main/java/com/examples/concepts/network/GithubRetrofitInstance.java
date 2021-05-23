package com.examples.concepts.network;

import com.examples.concepts.utils.Constants;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GithubRetrofitInstance {

    private static Retrofit retrofit;

    static HttpLoggingInterceptor logging = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);

    static OkHttpClient.Builder client = new OkHttpClient.Builder()
            .addInterceptor(logging);

    public static Retrofit getRetrofitClient() {
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.GITHUB_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client.build())
                    .build();
        }

        return retrofit;
    }

}
