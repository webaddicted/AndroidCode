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
import com.example.deepaksharma.androidcode.databinding.FragmentSpechToTextBinding;
import com.example.deepaksharma.androidcode.global.constant.AppConstant;
import com.example.deepaksharma.androidcode.view.base.BaseFragment;
import com.example.deepaksharma.androidcode.view.home.HomeActivity;
import com.example.deepaksharma.androidcode.viewModel.home.HomeViewModel;

import java.util.Locale;

public class SpeechToTextFragment extends BaseFragment {
    public static final String TAG = SpeechToTextFragment.class.getSimpleName();
    private FragmentSpechToTextBinding mBinding;
    private TextToSpeech mTextToSpeechListener;
    private HomeViewModel mHomeViewModel;

    public static SpeechToTextFragment getInstance(Bundle bundle) {
        SpeechToTextFragment fragment = new SpeechToTextFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_spech_to_text;
    }

    @Override
    protected void onViewsInitialized(ViewDataBinding binding, View view) {
        mBinding = (FragmentSpechToTextBinding) binding;
        mHomeViewModel = ViewModelProviders.of(getActivity()).get(HomeViewModel.class);
        init();
        clickListener();
    }

    private void init() {
        mTextToSpeechListener = new TextToSpeech(getActivity(), status -> mTextToSpeechListener.setLanguage(Locale.UK));
        mHomeViewModel.mSpeechToText.observe(this, strings -> {
            if (strings != null && strings.size() > 0) {
                mBinding.txtSpeechToText.setText(strings.get(0));
            }
        });
    }

    private void clickListener() {
        mBinding.btnTextToSpeech.setOnClickListener(this);
        mBinding.imgSpeechToText.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.btn_text_to_speech:
                String edtText = mBinding.edtText.getText().toString();
                mTextToSpeechListener.speak(edtText, TextToSpeech.QUEUE_FLUSH, null);
                break;
            case R.id.img_speech_to_text:
                speechToTextOutput();
                break;
        }
    }

    /**
     * Showing google speech input dialog
     */
    private void speechToTextOutput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Say something");
        try {
            startActivityForResult(intent, AppConstant.REQUEST_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException exp) {
            Log.d(TAG, "speechToTextOutput: " + exp.toString());
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        ((HomeActivity) getActivity()).showBackBtn();
        ((HomeActivity) getActivity()).setToolbarTitle(getResources().getString(R.string.speech_to_text_title));
    }
}
