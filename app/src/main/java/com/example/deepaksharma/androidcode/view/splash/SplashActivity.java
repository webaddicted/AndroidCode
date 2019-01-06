package com.example.deepaksharma.androidcode.view.splash;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.deepaksharma.androidcode.R;
import com.example.deepaksharma.androidcode.databinding.ActivitySplashBinding;

public class SplashActivity extends AppCompatActivity {
private ActivitySplashBinding mBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_splash);
    }
}
