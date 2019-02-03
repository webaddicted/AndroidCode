package com.example.deepaksharma.androidcode.view.splash;

import android.content.Intent;
import android.databinding.ViewDataBinding;
import android.os.Handler;

import com.example.deepaksharma.androidcode.R;
import com.example.deepaksharma.androidcode.databinding.ActivitySplashBinding;
import com.example.deepaksharma.androidcode.global.constant.AppConstant;
import com.example.deepaksharma.androidcode.view.base.BaseActivity;
import com.example.deepaksharma.androidcode.view.home.HomeActivity;

public class SplashActivity extends BaseActivity {
    private ActivitySplashBinding mBinding;

    @Override
    public int getLayout() {
        return R.layout.activity_splash;
    }

    @Override
    public void initUI(ViewDataBinding binding) {
        mBinding = (ActivitySplashBinding) binding;
        navigateToNext();
    }

    /**
     * navigate to welcome activity after Splash timer Delay
     */
    private void navigateToNext() {
        new Handler().postDelayed(() -> {
            startActivity(new Intent(SplashActivity.this, HomeActivity.class));
            finish();
        }, AppConstant.SPLASH_DELAY);
    }
}