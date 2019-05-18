package com.example.deepaksharma.androidcode.global.api.apiUtils;


import com.example.deepaksharma.androidcode.global.api.AppService;
import com.example.deepaksharma.androidcode.global.api.apiLivedataUtils.LiveDataCallAdapterFactory;
import com.example.deepaksharma.androidcode.global.constant.ApiConstant;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AppRetrofit {
    private static AppRetrofit instance;
    private final AppService appService;

    public AppService getAppService() {
        return appService;
    }

    public static AppRetrofit getInstance() {
        if (instance == null) instance = new AppRetrofit();
        return instance;
    }

    private AppRetrofit() {
        appService = provideService();
    }

    private AppService provideService() {
        // To show the Api Request & Params
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.NONE);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.connectTimeout(ApiConstant.API_TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(ApiConstant.API_TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(ApiConstant.API_TIME_OUT, TimeUnit.SECONDS);
        httpClient.addInterceptor(logging).addInterceptor(new AuthTokenRefreshInterceptor());

        return new Retrofit.Builder()
                .baseUrl(ApiConstant.BASE_URL)
                .addConverterFactory(new ToStringConverter())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(new LiveDataCallAdapterFactory())
                .client(httpClient.build()).build()
                .create(AppService.class);
    }

//    public AppService getAppService(Converter.Factory gsonConverter) {
//        // To show the Api Request & Params
//        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
//        logging.setLevel(HttpLoggingInterceptor.Level.NONE);
//
//        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
//        httpClient.addInterceptor(logging);
//
//        return new Retrofit.Builder()
//                .baseUrl(ApiConstant.BASE_URL)
//                .addConverterFactory(gsonConverter)
//                .addCallAdapterFactory(new LiveDataCallAdapterFactory())
//                .client(httpClient.build())
//                .build()
//                .create(AppService.class);
//    }

}
