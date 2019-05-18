package com.example.deepaksharma.androidcode.global.api.apiUtils;



import com.example.deepaksharma.androidcode.global.api.AppService;
import com.example.deepaksharma.androidcode.global.api.apiLivedataUtils.LiveDataCallAdapterFactory;
import com.example.deepaksharma.androidcode.global.constant.ApiConstant;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AppRetrofit {
    private static AppRetrofit instance;
    public AppService getAppService() {
        return appService;
    }
    private final AppService appService;

    private static void initInstance() {
        if (instance == null) {
            // Create the instance
            instance = new AppRetrofit();
        }
    }

    public static AppRetrofit getInstance() {
        // Return the instance
        initInstance();
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
        /*httpClient.connectTimeout(100, TimeUnit.SECONDS)
                .readTimeout(100, TimeUnit.SECONDS);*/
        httpClient.addInterceptor(logging);

        return new Retrofit.Builder()
                .baseUrl(ApiConstant.BASE_URL).addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(new LiveDataCallAdapterFactory())
                .client(httpClient.build()).build().create(AppService.class);
    }

    public AppService getAppService(Converter.Factory gsonConverter) {
        // To show the Api Request & Params
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.NONE);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);

        return new Retrofit.Builder()
                .baseUrl(ApiConstant.BASE_URL)
                .addConverterFactory(gsonConverter)
                .addCallAdapterFactory(new LiveDataCallAdapterFactory())
                .client(httpClient.build())
                .build()
                .create(AppService.class);
    }

}
