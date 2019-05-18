package com.example.deepaksharma.androidcode.global.api.apiUtils;

import android.os.Bundle;

import com.example.deepaksharma.androidcode.R;
import com.example.deepaksharma.androidcode.global.AppApplication;
import com.example.deepaksharma.androidcode.global.constant.ApiConstant;
import com.example.deepaksharma.androidcode.global.constant.IntentKeyPool;
import com.example.deepaksharma.androidcode.view.fragment.TaskListFragment;
import com.example.deepaksharma.androidcode.view.loader.LoaderDialog;
import com.example.deepaksharma.androidcode.view.loader.LoaderFragment;

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
    private LoaderFragment mLoaderFrm;
    private int mLoaderPos = -1;
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request.Builder builder = request.newBuilder();
        String reqUrl = request.url().toString();
//        {START show loader on the base of loader position}
        if (reqUrl.contains("loaderPos=1")) {
            showLoader();
            mLoaderPos = ApiConstant.LOADER_POS_CENTRE;
            reqUrl.replace("&loaderPos=1", "");
            builder.url(reqUrl);
        } else if (reqUrl.contains("loaderPos=2")) {
            showLoader();
            mLoaderPos = ApiConstant.LOADER_POS_BOTTOM;
            reqUrl.replace("&loaderPos=2", "");
            builder.url(reqUrl);
        }
//        {END show loader on the base of loader position}
//        Add header & change current url below then req api
        /*reqBuilder.header(ApiConstant.HEADER_CONTENT_TYPE, ApiConstant.HEADER_CONTENT_TYPE_VALUE);
        reqBuilder.header(ApiConstant.HEADER_CONTENT_TYPE, ApiConstant.HEADER_CONTENT_TYPE_VALUE);
        builder.url("change url here").toString();*/
        request = builder.build();
        Response response = chain.proceed(request);
        if (response.code() == RESPONSE_UNAUTHORIZED_401 || response.code() == RESPONSE_UNAUTHORIZED_403) {
            synchronized (this) {
                request = builder.build();
                Response retryResponse = chain.proceed(request);
                if (retryResponse.code() == RESPONSE_CODE_200) {
                    if (mLoaderPos > 0) hideLoader();
                    return retryResponse;
                } else {
                    //logout if response code is not 200
                    if (mLoaderPos > 0) hideLoader();
                    return retryResponse;
                }
            }
        }
        if (mLoaderPos > 0) hideLoader();
        return chain.proceed(builder.build());
    }

    private void showLoader() {
//        mLoaderDialog = new LoaderDialog();
//        mLoaderDialog.show(AppApplication.mSupportManager,LoaderDialog.TAG);
        Bundle bundle = new Bundle();
        bundle.putInt(IntentKeyPool.LOADER_POSITION, mLoaderPos);
        mLoaderFrm = LoaderFragment.getInstance(bundle);
        mLoaderFrm.navigateAddFrag(R.id.loader_container, mLoaderFrm);
    }

    private void hideLoader() {
//        if (mLoaderDialog != null && mLoaderDialog.isVisible()) mLoaderDialog.dismiss();
        if (mLoaderFrm != null) {
            AppApplication.mSupportManager.beginTransaction().
                    remove(AppApplication.mSupportManager.findFragmentById(R.id.loader_container)).commit();
        }

    }
}
