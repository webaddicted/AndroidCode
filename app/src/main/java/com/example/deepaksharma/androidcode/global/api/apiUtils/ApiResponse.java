package com.example.deepaksharma.androidcode.global.api.apiUtils;

import android.support.annotation.Nullable;
import android.util.Log;

import java.io.IOException;
import java.net.UnknownHostException;

import retrofit2.Response;

/**
 * Generic class to hold response from Apis
 *
 * @param <T> : respective response model class
 */
public class ApiResponse<T> {

    public final int code;
    @Nullable
    public final T body;
    @Nullable
    public final String errorMessage;

    public ApiResponse(Throwable error) {
        if (error instanceof UnknownHostException)
            code = ApiResponseStatusCode.INTERNET_ERROR;
        else
            code = ApiResponseStatusCode.DATABASE_ERROR;
        body = null;
        errorMessage = error.getMessage();
    }

    public ApiResponse(Response<T> response) {
        code = response.code();
        if (response.isSuccessful()) {
            body = response.body();
            errorMessage = null;
        } else {
            String message = null;
            if (response.errorBody() != null) {
                try {
                    message = response.errorBody().string();
                } catch (IOException ignored) {
                    Log.d("TAG", "ApiResponse: "+ignored);
                }
            }
            if (message == null || message.trim().length() == 0) {
                message = response.message();
            }
            errorMessage = message;
            body = null;
        }
    }

    public boolean isSuccessful() {
        return code >= ApiResponseStatusCode.SUCCESS_200 && code < ApiResponseStatusCode.SUCCESS_300;
    }
}
