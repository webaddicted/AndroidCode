package com.example.deepaksharma.androidcode.view.fragment.splash;

import android.content.Intent;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.example.deepaksharma.androidcode.R;
import com.example.deepaksharma.androidcode.databinding.ActivitySplashBinding;
import com.example.deepaksharma.androidcode.global.constant.AppConstant;
import com.example.deepaksharma.androidcode.view.base.BaseFragment;
import com.example.deepaksharma.androidcode.view.onBoarding.OnBoardActivity;

public class SplashFragment extends BaseFragment {
    public static final String TAG = SplashFragment.class.getSimpleName();
    private ActivitySplashBinding mBinding;

    public static SplashFragment getInstance(Bundle bundle) {
        SplashFragment fragment = new SplashFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void onViewsInitialized(ViewDataBinding binding, View view) {
        mBinding = (ActivitySplashBinding) binding;
        init();
    }

    private void init() {
        new Handler().postDelayed(() -> {
            startActivity(new Intent(getActivity(), OnBoardActivity.class));
            getActivity().finish();
        }, AppConstant.SPLASH_DELAY);
    }

}
