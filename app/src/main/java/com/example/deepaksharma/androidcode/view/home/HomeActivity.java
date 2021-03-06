package com.example.deepaksharma.androidcode.view.home;

import android.location.Location;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.deepaksharma.androidcode.R;
import com.example.deepaksharma.androidcode.databinding.ActivityHomeBinding;
import com.example.deepaksharma.androidcode.global.constant.AppConstant;
import com.example.deepaksharma.androidcode.view.base.BaseLocation;
import com.example.deepaksharma.androidcode.view.fragment.TaskListFragment;
import com.example.deepaksharma.androidcode.viewModel.home.HomeViewModel;
import com.theartofdev.edmodo.cropper.CropImage;

import java.util.ArrayList;

public class HomeActivity extends BaseLocation implements View.OnClickListener {
    private static final String TAG = HomeActivity.class.getSimpleName();
    private ActivityHomeBinding mBinding;
    private ArrayList<String> imagesEncodedList;
    private String imageEncoded;
    private HomeViewModel mHomeViewModel;

    @Override
    public int getLayout() {
        return R.layout.activity_home;
    }

    @Override
    public void initUI(ViewDataBinding binding) {
        mBinding = (ActivityHomeBinding) binding;
        mHomeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        init();
        clickListener();
    }

    private void init() {
        navigateScreen(TaskListFragment.TAG);
    }

    private void clickListener() {
        mBinding.include.imgBack.setOnClickListener(this);
    }

    public void setToolbarTitle(String title) {
        mBinding.include.txtTitle.setText(title);
    }

    public void showBackBtn() {
        mBinding.include.imgBack.setVisibility(View.VISIBLE);
    }

    public void hideBackBtn() {
        mBinding.include.imgBack.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                onBackPressed();
                break;
        }
    }

    /**
     * navigate on fragment
     *
     * @param tag represent navigation activity
     */
    private void navigateScreen(String tag) {
        Fragment frm;
        if (tag.equals(TaskListFragment.TAG)) {
            frm = TaskListFragment.getInstance(getIntent().getExtras());
            navigateFragment(R.id.container, frm, false);
        }
    }

    @Override
    public void getCurrentLocation(@NonNull Location location, @NonNull String address) {
        Log.d(TAG, "getCurrentLocation: getLatitude ->  " + location.getLatitude() + "   getLongitude  ->   " + location.getLongitude());
        mHomeViewModel.setCurrentLocationAddress(location, address);
        if (address != null)
            Log.d(TAG, "getCurrentLocation: getLatitude ->  " + location.getLatitude() + "   getLongitude  ->   " + location.getLongitude() + "  address  ->  " + address);
    }

}

