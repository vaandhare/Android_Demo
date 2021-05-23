package com.examples.concepts.network;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.examples.concepts.utils.Constants.TMDB_BASE_URL;

public class RetrofitInstance {

    private static Retrofit retrofit;

    static  HttpLoggingInterceptor logging = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);

    static OkHttpClient.Builder client = new OkHttpClient.Builder()
            .addInterceptor(logging);


    public static Retrofit getRetrofitClient() {
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(TMDB_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client.build())
                    .build();
        }

        return retrofit;
    }
}
