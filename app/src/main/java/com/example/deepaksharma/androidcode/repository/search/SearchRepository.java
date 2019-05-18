package com.example.deepaksharma.androidcode.repository.search;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.deepaksharma.androidcode.global.api.AppExecutors;
import com.example.deepaksharma.androidcode.global.api.apiModel.Resource;
import com.example.deepaksharma.androidcode.global.api.apiRepository.NetworkBoundWtDbRes;
import com.example.deepaksharma.androidcode.global.api.apiUtils.ApiResponse;
import com.example.deepaksharma.androidcode.global.api.apiUtils.AppRetrofit;
import com.example.deepaksharma.androidcode.model.search.SearchRespo;


public class SearchRepository {

    private final AppExecutors appExecutors;

    public SearchRepository() {
        this.appExecutors = new AppExecutors();
    }
    public LiveData<Resource<SearchRespo>> searchResult(String url) {
        return new NetworkBoundWtDbRes<SearchRespo, SearchRespo>(appExecutors) {
            @NonNull
            @Override
            protected LiveData<ApiResponse<SearchRespo>> createCall() {
                return AppRetrofit.getInstance().getAppService().getSearchResult(url);
            }
        }.asLiveData();
    }
}
