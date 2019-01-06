package com.example.deepaksharma.androidcode.global.api;

import com.example.deepaksharma.androidcode.BuildConfig;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static Retrofit retrofit = null;
    private static String mToken;

    public static ApiList getApis(String token) {
        mToken = token;
        if (retrofit == null) {
            setUpClient();
        }
        return retrofit.create(ApiList.class);
    }


    private static void setUpClient() {
        Interceptor HEADER_INTERCEPTORS = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request().newBuilder()
                        .addHeader(ApiConstants.TOKEN, mToken).build();
//                        .addHeader("Content-Type", "application/json")
//                        .addHeader(ApiConstant.VERSION_KEY, ApiConstant.APP_VERSION).build();
                return chain.proceed(request);
            }
        };

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        if (BuildConfig.DEBUG) {
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);
        }
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(httpLoggingInterceptor)
                .addNetworkInterceptor(HEADER_INTERCEPTORS)
                .build();
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        retrofit = new Retrofit.Builder()
                .baseUrl(ApiConstants.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(new NullOnEmptyConverterFactory())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
//        retrofit = new Retrofit.Builder()
//                .baseUrl(ApiConstants.root)
//                .client(okHttpClient)
//                .addConverterFactory(GsonConverterFactory.create())
//                .addConverterFactory(new NullOnEmptyConverterFactory())
//                .build();
    }

}
