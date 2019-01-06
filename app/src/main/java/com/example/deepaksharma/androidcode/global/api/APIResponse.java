package com.example.deepaksharma.androidcode.global.api;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Deepak Sharma on 06/01/19.
 */

public class APIResponse {

    private static String TAG = APIResponse.class.getSimpleName();
//    Step 1-> for call api

//    private void loginAppi() {
//        Retrofit retrofit = ApiLists.retrofit;
//        ApiLists apiList = retrofit.create(ApiLists.class);
//        Call<JsonElement> loginApiCall = apiList.loginApi("kjdf", "fkldngdkl");
//        APIResponse.callRetrofit(loginApiCall, "LoginApi", LoginActivity.this, this);
//    }

//    Step 2-> make a listener & implement in calling api Activity

//    public interface ApiListener {
//        void success(String strApiName, Object response);
//        void error(String strApiName, String error);
//        void failure(String strApiName, String message);
//    }

//    Step 3-> implement interface response

//    @Override
//    public void success(String strApiName, String response) {
//        if (strApiName.equals("LoginApi")) {
//            Log.d(TAG, "onResponse: success: LoginApi response===>"+response);
////            NearByNurse nearByNurse = (NearByNurse) response.body(); // use the user object for the other fields
//        }
//    }
//
//    @Override
//    public void error(String strApiName, String error) {
//        Log.d(TAG, "onResponse: Error: "+strApiName +"  Error Response===>"+error);
//    }
//
//    @Override
//    public void failure(String strApiName, String message) {
//        Log.d(TAG, "onResponse: Failure: "+strApiName +"  Failure Response===>"+message);
//    }

    public static <T> void callRetrofit(Call<T> call, final String apiName, Context context, final ApiListener apiListener) {

        final ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        call.enqueue(new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                if (response.isSuccessful()) {
                    if (response.body()!=null) {
                        Log.d(TAG, "onResponse: " + response.body().toString());
                        apiListener.success(apiName, response.body());
                    }else {
                        apiListener.success(apiName, null);
                    }
                    progressDialog.dismiss();
                } else {

                    try {
                        Log.d(TAG, "onResponse: " + response.errorBody().string());
                        apiListener.error(apiName, response.errorBody().string());
                        progressDialog.dismiss();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.toString());
                apiListener.failure(apiName, t.toString());
                progressDialog.dismiss();

            }
        });

    }

    public interface ApiListener {
        void success(String strApiName, Object response);

        void error(String strApiName, String error);

        void failure(String strApiName, String message);
    }

}
