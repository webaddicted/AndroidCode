package com.example.deepaksharma.androidcode.global.api;


import androidx.lifecycle.LiveData;

import com.example.deepaksharma.androidcode.global.api.apiUtils.ApiResponse;
import com.example.deepaksharma.androidcode.model.search.SearchRespo;

import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;


/**
 * All API services, with their Url, CloudinaryResponse type, Request type and Request method(eg. GET, POST)
 */
public interface AppService {
    @GET
    LiveData<ApiResponse<SearchRespo>> getSearchResult(@Url String url
    , @Query("loaderPos")int loaderPos);

    //method to  call the api to do the login
//    @POST(ApiConstant.MANUAL_LOGIN)
//    LiveData<ApiResponse<LoginResponse>> doManualLogin(@Body LoginRequest manualLoginRequest);
}

