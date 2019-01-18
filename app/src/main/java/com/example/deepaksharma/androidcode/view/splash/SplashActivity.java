package com.example.deepaksharma.androidcode.view.splash;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Handler;
import android.os.Bundle;

import com.example.deepaksharma.androidcode.R;
import com.example.deepaksharma.androidcode.databinding.ActivitySplashBinding;
import com.example.deepaksharma.androidcode.global.constant.AppConstant;
import com.example.deepaksharma.androidcode.view.BaseActivity;
import com.example.deepaksharma.androidcode.view.home.HomeActivity;

public class SplashActivity extends BaseActivity {
    private ActivitySplashBinding mBinding;

    @Override
    public int getLayout() {
        return R.layout.activity_home;
    }

    @Override
    public void initUI(ViewDataBinding binding) {
        mBinding = (ActivitySplashBinding) binding;
        navigateToNext();
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_splash);
//        getSupportActionBar().hide();
//        navigateToNext();
//    }


    }

    /**
     * navigate to welcome activity after Splash timer Delay
     */
    private void navigateToNext() {
        new Handler().postDelayed(() -> {
            startActivity(new Intent(SplashActivity.this, HomeActivity.class));
        }, AppConstant.SPLASH_DELAY);
    }
}