package com.example.deepaksharma.androidcode.global.api.apiUtils;

import com.example.deepaksharma.androidcode.global.AppApplication;
import com.example.deepaksharma.androidcode.view.dialog.LoaderDialog;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthTokenRefreshInterceptor implements Interceptor {
    private static final String TAG = AuthTokenRefreshInterceptor.class.getSimpleName();
    private static final int RESPONSE_CODE_200 = 200;
    private static final int RESPONSE_UNAUTHORIZED_401 = 401;
    private static final int RESPONSE_UNAUTHORIZED_403 = 403;
    private LoaderDialog mLoaderDialog;

    @Override
    public Response intercept(Chain chain) throws IOException {
        showLoader();
        Request request = chain.request();
        Request.Builder builder = request.newBuilder();
        String reqUrl = request.url().toString();
//        Add header & change current url below then req api
        /*reqBuilder.header(ApiConstant.HEADER_CONTENT_TYPE, ApiConstant.HEADER_CONTENT_TYPE_VALUE);
        reqBuilder.header(ApiConstant.HEADER_CONTENT_TYPE, ApiConstant.HEADER_CONTENT_TYPE_VALUE);
        builder.url("change url here").toString();*/
        request = builder.build();
        Response response = chain.proceed(request);
        if (response.code() != RESPONSE_UNAUTHORIZED_401 || response.code() != RESPONSE_UNAUTHORIZED_403) {
            synchronized (this) {
                request = builder.build();
                Response retryResponse = chain.proceed(request);
                if (retryResponse.code() == RESPONSE_CODE_200) {
                    hideLoader();
                    return retryResponse;
                } else {
                    //logout if response code is not 200
                    hideLoader();
                    return retryResponse;
                }
            }
        }
        hideLoader();
        return chain.proceed(builder.build());
    }

    private void showLoader() {
        mLoaderDialog = new LoaderDialog();
        mLoaderDialog.show(AppApplication.mSupportManager,LoaderDialog.TAG);
    }

    private void hideLoader() {
        if (mLoaderDialog != null && mLoaderDialog.isVisible()) mLoaderDialog.dismiss();
    }
}
