/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.deepaksharma.androidcode.global.api.apiRepository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.deepaksharma.androidcode.global.api.AppExecutors;
import com.example.deepaksharma.androidcode.global.api.apiLivedataUtils.AbsentLiveData;
import com.example.deepaksharma.androidcode.global.api.apiLivedataUtils.ApiUtil;
import com.example.deepaksharma.androidcode.global.api.apiModel.Resource;
import com.example.deepaksharma.androidcode.global.api.apiUtils.ApiResponse;

import static com.example.deepaksharma.androidcode.global.api.apiUtils.ApiResponseStatusCode.ZERO_STATUS_CODE;


/**
 * A generic class that can provide a resource backed by network only.
 *
 * @param <ResultType>
 * @param <RequestType>
 */
public abstract class NetworkBoundWtDbRes<ResultType, RequestType> {
    private final AppExecutors appExecutors;

    private final MediatorLiveData<Resource<ResultType>> result = new MediatorLiveData<>();

    @MainThread
    protected NetworkBoundWtDbRes(AppExecutors appExecutors) {
        this.appExecutors = appExecutors;
        result.setValue((Resource<ResultType>) Resource.loading(null, ZERO_STATUS_CODE));
        final LiveData<ResultType> dbSource = AbsentLiveData.create();

        result.addSource(dbSource, new Observer<ResultType>() {
            @Override
            public void onChanged(@Nullable ResultType data) {
                result.removeSource(dbSource);
                fetchFromNetwork(dbSource);
            }
        });
    }

    private void fetchFromNetwork(final LiveData<ResultType> dbSource) {
        final LiveData<ApiResponse<ResultType>> apiResponse = createCall();

        result.addSource(apiResponse, new Observer<ApiResponse<ResultType>>() {
            @Override
            public void onChanged(@Nullable final ApiResponse<ResultType> resultTypeApiResponse) {
                result.removeSource(apiResponse);
                result.removeSource(dbSource);
                //noinspection ConstantConditions
                if (resultTypeApiResponse.isSuccessful()) {
                    appExecutors.diskIO().execute(new Runnable() {
                        @Override
                        public void run() {
                            appExecutors.mainThread().execute(() -> result.addSource(ApiUtil.successCall(resultTypeApiResponse.body), new Observer<ResultType>() {
                                        @Override
                                        public void onChanged(@Nullable ResultType resultType) {
                                            result.setValue(Resource.success(resultType, resultTypeApiResponse.code));
                                        }
                                    }
                                    )
                            );
                        }
                    });
                } else {
                    onFetchFailed();
                    result.addSource(dbSource,
                            newData -> result.setValue(Resource.error(resultTypeApiResponse.errorMessage, newData, resultTypeApiResponse.code)));
                }
            }
        });
    }

    protected void onFetchFailed() {
    }

    public LiveData<Resource<ResultType>> asLiveData() {
        return result;
    }


    @NonNull
    @MainThread
    protected abstract LiveData<ApiResponse<ResultType>> createCall();
}
