package com.example.deepaksharma.androidcode.view.base;

import android.Manifest;
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
import android.util.Log;

import com.example.deepaksharma.androidcode.R;
import com.example.deepaksharma.androidcode.global.FileUtils;
import com.example.deepaksharma.androidcode.global.PermissionsHandler;
import com.example.deepaksharma.androidcode.global.constant.AppConstant;
import com.example.deepaksharma.androidcode.global.image.ImagePicker;
import com.example.deepaksharma.androidcode.model.NetworkListenerBean;
import com.example.deepaksharma.androidcode.model.eventBus.EventBusListener;
import com.example.deepaksharma.androidcode.view.interfaces.LayoutListener;
import com.example.deepaksharma.androidcode.viewModel.home.HomeViewModel;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import static com.example.deepaksharma.androidcode.view.fragment.GoogleMapFragment.REQUEST_PLACE;

public class BaseActivity extends AppCompatActivity implements LayoutListener, PermissionsHandler.PermissionListener {
    private static final String TAG = BaseActivity.class.getSimpleName();
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
                case ImagePicker.REQUEST_CAMERA_VIDEO:
                case ImagePicker.SELECT_FILE_FROM_GALLERY:
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
                case REQUEST_PLACE:
                    Place searchPlace = PlaceAutocomplete.getPlace(this, data);
                    mHomeViewModel.mSearchedPlace.postValue(searchPlace);
                    break;
                case PlaceAutocomplete.RESULT_ERROR:
                    Status status = PlaceAutocomplete.getStatus(this, data);
                    Log.d(TAG, "onActivityResult: "+status.getStatusMessage());
                    break;
            }
        }
    }

    private void startCropImageActivity(Uri imageUri) {
        CropImage.activity(imageUri)
                .setGuidelines(CropImageView.Guidelines.ON)
                .setActivityTitle(getResources().getString(R.string.crop_image))
                .setMultiTouchEnabled(true)
                .start(this);
    }

    protected void checkStoragePermission() {
        List<String> multiplePermission = new ArrayList<>();
        multiplePermission.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        multiplePermission.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        multiplePermission.add(Manifest.permission.CAMERA);
        if (PermissionsHandler.checkMultiplePermission(multiplePermission)) {
            FileUtils.createApplicationFolder();
            mHomeViewModel.mIsPermissionGranted.postValue(true);
        } else
            PermissionsHandler.requestMultiplePermission(multiplePermission, this);
    }

    protected void checkLocationPermission() {
        List<String> multiplePermission = new ArrayList<>();
        multiplePermission.add(Manifest.permission.ACCESS_FINE_LOCATION);
        multiplePermission.add(Manifest.permission.ACCESS_COARSE_LOCATION);
        if (PermissionsHandler.checkMultiplePermission(multiplePermission)) {
        } else PermissionsHandler.requestMultiplePermission(multiplePermission, this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        PermissionsHandler.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onPermissionGranted(List<String> mCustomPermission) {
        mHomeViewModel.mIsPermissionGranted.postValue(true);
    }

    @Override
    public void onPermissionDenied(List<String> mCustomPermission) {
        mHomeViewModel.mIsPermissionGranted.postValue(false);
    }

    @Subscribe
    public void NetworkChangeListener(NetworkListenerBean networkListenerBean) {
    }
}
