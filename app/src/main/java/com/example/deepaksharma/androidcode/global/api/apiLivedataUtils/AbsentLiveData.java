package com.example.deepaksharma.androidcode.global.api.apiLivedataUtils;

import android.arch.lifecycle.LiveData;


public class AbsentLiveData extends LiveData {
    private AbsentLiveData() {
        postValue(null);
    }

    public static <T> LiveData<T> create() {
        //noinspection unchecked
        return new AbsentLiveData();
    }
}