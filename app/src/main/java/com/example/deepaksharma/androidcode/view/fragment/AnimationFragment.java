package com.example.deepaksharma.androidcode.view.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;

import com.example.deepaksharma.androidcode.R;
import com.example.deepaksharma.androidcode.databinding.FragmentAnimationBinding;
import com.example.deepaksharma.androidcode.databinding.FragmentSpechToTextBinding;
import com.example.deepaksharma.androidcode.global.constant.AppConstant;
import com.example.deepaksharma.androidcode.view.base.BaseFragment;
import com.example.deepaksharma.androidcode.view.home.HomeActivity;
import com.example.deepaksharma.androidcode.viewModel.home.HomeViewModel;

import java.util.Locale;

public class AnimationFragment extends BaseFragment {
    public static final String TAG = AnimationFragment.class.getSimpleName();
    private FragmentAnimationBinding mBinding;

    public static AnimationFragment getInstance(Bundle bundle) {
        AnimationFragment fragment = new AnimationFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_animation;
    }

    @Override
    protected void onViewsInitialized(ViewDataBinding binding, View view) {
        mBinding = (FragmentAnimationBinding) binding;
        init();
        clickListener();
    }

    private void init() {
    }

    private void clickListener() {
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.btn_text_to_speech:
                break;
            case R.id.img_speech_to_text:
                break;
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        ((HomeActivity) getActivity()).showBackBtn();
        ((HomeActivity) getActivity()).setToolbarTitle(getResources().getString(R.string.animation_title));
    }
}
