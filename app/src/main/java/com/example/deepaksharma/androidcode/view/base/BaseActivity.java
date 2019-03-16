package com.example.deepaksharma.androidcode.view.base;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.net.Uri;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.deepaksharma.androidcode.R;
import com.example.deepaksharma.androidcode.global.constant.AppConstant;
import com.example.deepaksharma.androidcode.global.image.ImagePicker;
import com.example.deepaksharma.androidcode.model.eventBus.EventBusListener;
import com.example.deepaksharma.androidcode.utils.GlobalUtilities;
import com.example.deepaksharma.androidcode.view.interfaces.LayoutListener;
import com.example.deepaksharma.androidcode.viewModel.home.HomeViewModel;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

public class BaseActivity extends AppCompatActivity implements LayoutListener {
    private HomeViewModel mHomeViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int layoutResId = getLayout();
        overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);
        getSupportActionBar().hide();
        ViewDataBinding binding = null;
        mHomeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        if (!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this);
        if (layoutResId != 0) {
            try {
                binding = DataBindingUtil.setContentView(this, layoutResId);
                initUI(binding);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public int getLayout() {
        return 0;
    }

    @Override
    public void initUI(ViewDataBinding binding) {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void EventBusListener(EventBusListener eventBusListener) {
    }

    protected void navigateFragment(int layoutContainer, Fragment fragment, boolean isEnableBackStack) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(layoutContainer, fragment);
        if (isEnableBackStack)
            fragmentTransaction.addToBackStack(fragment.getClass().getSimpleName());
        fragmentTransaction.commitAllowingStateLoss();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case AppConstant.REQUEST_CODE_SPEECH_INPUT:
                    ArrayList<String> resultSpeech = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    mHomeViewModel.mSpeechToText.postValue(resultSpeech);
                    break;
                case ImagePicker.REQUEST_CAMERA:
                case ImagePicker.SELECT_FILE:
                case ImagePicker.PICK_IMAGE_MULTIPLE:
                    ImagePicker.onActivityResult(this, requestCode, resultCode, data);
                    break;
                case CropImage.PICK_IMAGE_CHOOSER_REQUEST_CODE:
                    Uri imageUri = CropImage.getPickImageResultUri(this, data);
                    startCropImageActivity(imageUri);
                    break;
                case CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE:
                case CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE:
                    CropImage.ActivityResult result = CropImage.getActivityResult(data);
                    mHomeViewModel.mCroppedImage.postValue(result);
                    break;
            }
        }
    }

    private void startCropImageActivity(Uri imageUri) {
        CropImage.activity(imageUri)
                .setGuidelines(CropImageView.Guidelines.ON)
                .setMultiTouchEnabled(true)
                .start(this);
    }


}
