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
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;

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
    private Animation animBlink, animFade, animZoom, animMove, animRotate, animFadeIn, animFadeOut,
            animZoomIn, animZoomOut, animSlideUp, animSlideDown, animBounce, animSequential;
    private Animation animTogether;

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
        animBlink = AnimationUtils.loadAnimation(getActivity(), R.anim.blink);
        animFade = AnimationUtils.loadAnimation(getActivity(), R.anim.fade);
        animZoom = AnimationUtils.loadAnimation(getActivity(), R.anim.zoom);
        animMove = AnimationUtils.loadAnimation(getActivity(), R.anim.move);
        animRotate = AnimationUtils.loadAnimation(getActivity(), R.anim.rotate);
        animFadeIn = AnimationUtils.loadAnimation(getActivity(), R.anim.fade_in);
        animFadeOut = AnimationUtils.loadAnimation(getActivity(), R.anim.fade_out);
        animZoomIn = AnimationUtils.loadAnimation(getActivity(), R.anim.zoom_in);
        animZoomOut = AnimationUtils.loadAnimation(getActivity(), R.anim.zoom_out);
        animSlideUp = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_up);
        animSlideDown = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_down);
        animBounce = AnimationUtils.loadAnimation(getActivity(), R.anim.bounce);
        animSequential = AnimationUtils.loadAnimation(getActivity(), R.anim.sequential);
        animTogether = AnimationUtils.loadAnimation(getActivity(), R.anim.together);

    }

    private void clickListener() {
        mBinding.btnBlink.setOnClickListener(this);
        mBinding.btnFade.setOnClickListener(this);
        mBinding.btnZoom.setOnClickListener(this);
        mBinding.btnMove.setOnClickListener(this);
        mBinding.btnRotate.setOnClickListener(this);
        mBinding.btnFadeIn.setOnClickListener(this);
        mBinding.btnFadeOut.setOnClickListener(this);
        mBinding.btnCrossFadeIn.setOnClickListener(this);
        mBinding.btnCrossFadeOut.setOnClickListener(this);
        mBinding.btnZoomIn.setOnClickListener(this);
        mBinding.btnZoomOut.setOnClickListener(this);
        mBinding.btnSlideUp.setOnClickListener(this);
        mBinding.btnSlideDown.setOnClickListener(this);
        mBinding.btnBounce.setOnClickListener(this);
        mBinding.btnSequence.setOnClickListener(this);
        mBinding.btnTogether.setOnClickListener(this);
        mBinding.btnLeftRight.setOnClickListener(this);
        mBinding.btnTopTobBottom.setOnClickListener(this);
        mBinding.btnBottomToTop.setOnClickListener(this);
        mBinding.btnRightToLeft.setOnClickListener(this);
        mBinding.btnLeftToRight.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.btn_blink:
                startAnimation(animBlink, mBinding.btnBlink, mBinding.txtBlink);
                break;
            case R.id.btn_fade:
                startAnimation(animFade, mBinding.btnFade, mBinding.txtFade);
                break;
            case R.id.btn_zoom:
                startAnimation(animZoom, mBinding.btnZoom, mBinding.txtZoom);
                break;
            case R.id.btn_move:
                startAnimation(animMove, mBinding.btnMove, mBinding.txtMove);
                break;
            case R.id.btn_rotate:
                startAnimation(animRotate, mBinding.btnRotate, mBinding.txtRotate);
                break;
            case R.id.btn_cross_fade_in:
                startAnimation(animFadeIn, mBinding.btnCrossFadeIn, mBinding.txtCrossFadeIn);
                break;
            case R.id.btn_cross_fade_out:
                startAnimation(animFadeOut, mBinding.btnCrossFadeOut, mBinding.txtCrossFadeOut);
                break;
            case R.id.btn_zoom_in:
                startAnimation(animZoomIn, mBinding.btnZoomIn, mBinding.txtZoomIn);
                break;
            case R.id.btn_zoom_out:
                startAnimation(animZoomOut, mBinding.btnZoomOut, mBinding.txtZoomOut);
                break;
            case R.id.btn_slide_up:
                startAnimation(animSlideUp, mBinding.btnSlideUp, mBinding.txtSlideUp);
                break;
            case R.id.btn_slide_down:
                startAnimation(animSlideDown, mBinding.btnSlideDown, mBinding.txtSlideDown);
                break;
            case R.id.btn_bounce:
                startAnimation(animBounce, mBinding.btnBounce, mBinding.txtBounce);
                break;
            case R.id.btn_sequence:
                startAnimation(animSequential, mBinding.btnSequence, mBinding.txtSequence);
                break;
            case R.id.btn_together:
                startAnimation(animTogether, mBinding.btnTogether, mBinding.txtTogether);
                break;
            case R.id.btn_left_right:
                leftRigtAnimation();
                break;
            case R.id.btn_top_tob_bottom:
                leftRigtAnimation();
                break;
            case R.id.btn_bottom_to_top:
                leftRigtAnimation();
                break;
            case R.id.btn_right_to_left:
                leftRigtAnimation();
                break;
            case R.id.btn_left_to_right:
                leftRigtAnimation();
                break;
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        ((HomeActivity) getActivity()).showBackBtn();
        ((HomeActivity) getActivity()).setToolbarTitle(getResources().getString(R.string.animation_title));
    }

    public void startAnimation(Animation animation, View btnView, View txtView) {
        btnView.startAnimation(animation);
        txtView.startAnimation(animation);
        mBinding.imageAnimation.startAnimation(animation);
    }

    public void leftRigtAnimation() {
        // top to bottom animation............................
        TranslateAnimation trans1 = new TranslateAnimation(0, 0, -100, 0);
        trans1.setDuration(1000);
        mBinding.btnTopTobBottom.setAnimation(trans1);

        //botom to top animation...........................
        TranslateAnimation trans2 = new TranslateAnimation(0, 0, 100, 0);
        trans2.setDuration(1000);
        mBinding.btnBottomToTop.setAnimation(trans2);

        //left to right animation.............................
        TranslateAnimation trans3 = new TranslateAnimation(100, 0, 0, 0);
        trans3.setDuration(1000);
        mBinding.btnLeftToRight.setAnimation(trans3);

        // right to left animation..............................
        TranslateAnimation trans4 = new TranslateAnimation(-100, 0, 0, 0);
        trans4.setDuration(1000);
        mBinding.btnRightToLeft.setAnimation(trans4);
    }
}
