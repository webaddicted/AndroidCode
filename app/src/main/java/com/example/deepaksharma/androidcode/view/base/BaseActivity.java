package com.example.deepaksharma.androidcode.view.base;

import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import com.example.deepaksharma.androidcode.R;
import com.example.deepaksharma.androidcode.global.AppApplication;
import com.example.deepaksharma.androidcode.global.FileUtils;
import com.example.deepaksharma.androidcode.global.PermissionsHandler;
import com.example.deepaksharma.androidcode.global.constant.AppConstant;
import com.example.deepaksharma.androidcode.global.image.MediaPickerUtils;
import com.example.deepaksharma.androidcode.model.NetworkListenerBean;
import com.example.deepaksharma.androidcode.model.eventBus.EventBusListener;
import com.example.deepaksharma.androidcode.viewModel.home.HomeViewModel;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import static com.example.deepaksharma.androidcode.view.fragment.GoogleMapFragment.REQUEST_PLACE;

public abstract class BaseActivity extends AppCompatActivity implements PermissionsHandler.PermissionListener, View.OnClickListener {
    private static final String TAG = BaseActivity.class.getSimpleName();
    private HomeViewModel mHomeViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int layoutResId = getLayout();
        overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);
        getSupportActionBar().hide();
        setNavigationColor(getResources().getColor(R.color.app_color));
        ViewDataBinding binding = null;
        AppApplication.setActivityInstance(this);
        AppApplication.mSupportManager = getSupportFragmentManager();
        mHomeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        fullScreen();
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

    private void fullScreen() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            if (window != null) {
                window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(Color.TRANSPARENT);
            }
        }
    }
    protected void setNavigationColor(int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(color);
        }
    }
    protected abstract int getLayout();

    protected abstract void initUI(ViewDataBinding binding);

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

    protected void navigateAddFragment(int layoutContainer, Fragment fragment, boolean isEnableBackStack) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(layoutContainer, fragment);
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
                case MediaPickerUtils.REQUEST_CAMERA_VIDEO:
                case MediaPickerUtils.REQUEST_SELECT_FILE_FROM_GALLERY:
                    MediaPickerUtils.onActivityResult(this, requestCode, resultCode, data);
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
                    Log.d(TAG, "onActivityResult: " + status.getStatusMessage());
                    break;
                case IntentIntegrator.REQUEST_CODE:
                    IntentResult mIntentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
                    mHomeViewModel.mBarCodeResult.postValue(mIntentResult);
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
        if (PermissionsHandler.checkMultiplePermission(this, multiplePermission)) {
            FileUtils.createApplicationFolder();
            mHomeViewModel.mIsPermissionGranted.postValue(true);
        } else
            PermissionsHandler.requestMultiplePermission(this, multiplePermission, this);
    }

    protected void checkLocationPermission() {
        List<String> multiplePermission = new ArrayList<>();
        multiplePermission.add(Manifest.permission.ACCESS_FINE_LOCATION);
        multiplePermission.add(Manifest.permission.ACCESS_COARSE_LOCATION);
        if (PermissionsHandler.checkMultiplePermission(this, multiplePermission)) {
        } else PermissionsHandler.requestMultiplePermission(this, multiplePermission, this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        PermissionsHandler.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
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
    @Override
    public void onClick(View v) {

    }
}
