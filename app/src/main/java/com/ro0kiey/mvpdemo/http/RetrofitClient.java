package com.ro0kiey.mvpdemo.http;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Ro0kieY on 2017/7/20.
 */

public class RetrofitClient {

    public static final String BASE_URL="http://gank.io/api/";

    private static Retrofit retrofit;
    private static ApiService apiService;
    protected static Gson gson;
    private static Object monitor = new Object();

    private RetrofitClient() {}

    static {
        gson = new GsonBuilder()
                //2017-07-20T08:45:18.437Z
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                .create();
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

    }

    public static ApiService getApiServiceInstance(){
        synchronized (monitor){
            if (apiService == null){
                apiService = retrofit.create(ApiService.class);
            }
            return apiService;
        }
    }
}
