package com.example.deepaksharma.androidcode.view.onBoarding;

import android.content.Intent;
import android.databinding.ViewDataBinding;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.deepaksharma.androidcode.R;
import com.example.deepaksharma.androidcode.databinding.ActivityOnboardingBinding;
import com.example.deepaksharma.androidcode.view.adapter.OnBordingViewPagerAdapter;
import com.example.deepaksharma.androidcode.view.base.BaseActivity;
import com.example.deepaksharma.androidcode.view.home.HomeActivity;

public class OnBoardActivity extends BaseActivity {
    private ActivityOnboardingBinding mBinding;
    private int[] layouts = new int[]{R.layout.welcome_slide1, R.layout.welcome_slide2, R.layout.welcome_slide3, R.layout.welcome_slide4};

    @Override
    protected int getLayout() {
        return R.layout.activity_onboarding;
    }

    @Override
    protected void initUI(ViewDataBinding binding) {
        mBinding = (ActivityOnboardingBinding) binding;
        init();
        clickListener();
        setAdapter();
    }

    private void init() {
        changeNavigatColor(0);
        mBinding.viewPager.addOnPageChangeListener(getChangeListener());
    }

    private void clickListener() {
        mBinding.btnGotIt.setOnClickListener(this);
        mBinding.btnNext.setOnClickListener(this);
        mBinding.btnSkip.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.btn_skip:
            case R.id.btn_got_it:
                startActivity(new Intent(OnBoardActivity.this, HomeActivity.class));
                finish();
                break;
            case R.id.btn_next:
                showNextSlide();
                break;
        }
    }

    private void setAdapter() {
        mBinding.viewPager.setAdapter(new OnBordingViewPagerAdapter(this, layouts));
        mBinding.dotsIndicator.setViewPager(mBinding.viewPager);
    }

    private ViewPager.OnPageChangeListener getChangeListener() {
        return new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                boolean isLastPage = (position == (layouts.length - 1));
                mBinding.btnNext.setVisibility(isLastPage ? View.GONE : View.VISIBLE);
                mBinding.btnSkip.setVisibility(isLastPage ? View.INVISIBLE : View.VISIBLE);
                mBinding.btnGotIt.setVisibility(isLastPage ? View.VISIBLE : View.GONE);
                changeNavigatColor(position);
            }

            @Override
            public void onPageScrolled(int arg, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int arg) {
            }
        };
    }
    private void changeNavigatColor(int position) {
        if (position == 0) setNavigationColor(getResources().getColor(R.color.bg_screen1));
        else if (position == 1) setNavigationColor(getResources().getColor(R.color.bg_screen2));
        else if (position == 2) setNavigationColor(getResources().getColor(R.color.bg_screen3));
        else if (position == 3) setNavigationColor(getResources().getColor(R.color.bg_screen4));

    }
    private void showNextSlide() {
        int nextIndex = mBinding.viewPager.getCurrentItem() + 1;
        if ((mBinding.viewPager != null) && (nextIndex < layouts.length))
            mBinding.viewPager.setCurrentItem(nextIndex);
    }
}