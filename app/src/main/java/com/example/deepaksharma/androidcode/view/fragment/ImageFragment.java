package com.example.deepaksharma.androidcode.view.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.deepaksharma.androidcode.R;
import com.example.deepaksharma.androidcode.databinding.FragmentImageBinding;
import com.example.deepaksharma.androidcode.global.StorageInfoUtils;
import com.example.deepaksharma.androidcode.global.constant.AppConstant;
import com.example.deepaksharma.androidcode.utils.GlobalUtilities;
import com.example.deepaksharma.androidcode.view.adapter.DeviceImageRecyclerViewAdapter;
import com.example.deepaksharma.androidcode.view.base.BaseFragment;
import com.example.deepaksharma.androidcode.view.home.HomeActivity;
import com.example.deepaksharma.androidcode.viewModel.home.HomeViewModel;
import com.theartofdev.edmodo.cropper.CropImage;

import java.io.File;
import java.io.Serializable;
import java.util.List;

public class ImageFragment extends BaseFragment {
    public static final String TAG = ImageFragment.class.getSimpleName();
    private FragmentImageBinding mBinding;
    private DeviceImageRecyclerViewAdapter mAdapter;
    private List<File> mFileList;
    private HomeViewModel mHomeViewModel;
    private int mSelectedPos = 0;

    public static ImageFragment getInstance(Bundle bundle) {
        ImageFragment fragment = new ImageFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_image;
    }

    @Override
    protected void onViewsInitialized(ViewDataBinding binding, View view) {
        mBinding = (FragmentImageBinding) binding;
        mHomeViewModel = ViewModelProviders.of(getActivity()).get(HomeViewModel.class);
        clickListener();
    }

    private void clickListener() {
        mBinding.btnAllImage.setOnClickListener(this);
        mBinding.btnCropImage.setOnClickListener(this);
        mBinding.btnCaptureImageBg.setOnClickListener(this);
        mBinding.btnWhatsApp.setOnClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        ((HomeActivity) getActivity()).showBackBtn();
        ((HomeActivity) getActivity()).setToolbarTitle(getResources().getString(R.string.device_image_title));
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.btn_all_image:
                checkPermission();
                break;
            case R.id.btn_crop_image:
                cropImage();
                break;
            case R.id.btn_capture_image_bg:
                captureImageInBg();
                break;
            case R.id.btn_whats_app:
                whatAppTypeImage();
                break;
        }
    }

    private void checkPermission() {
        checkStoragePermission();
        mHomeViewModel.mIsPermissionGranted.observe(this, permissionGranted -> {
            if (permissionGranted) setAdapter();
            else GlobalUtilities.showToast(getResources().getString(R.string.permission_require));
        });
    }

    private void setAdapter() {
        mBinding.loader.parentLoader.setVisibility(View.VISIBLE);
        mFileList = StorageInfoUtils.getAllDeviceImage(getActivity());
        mAdapter = new DeviceImageRecyclerViewAdapter(ImageFragment.this, mFileList);
        mBinding.rvImageShow.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        mBinding.rvImageShow.setAdapter(mAdapter);
        mBinding.loader.parentLoader.setVisibility(View.GONE);
    }

    private void cropImage() {
        CropImage.startPickImageActivity(getActivity());
        mHomeViewModel.mCroppedImage.observe(this, activityResult -> {
            if (activityResult != null) {
                GlobalUtilities.showToast(activityResult.getUri().toString());
            }
        });
    }

    private void captureImageInBg() {
    }

    private void whatAppTypeImage() {
    }

    public void onItemClick(int position) {
        mSelectedPos = position;
        navigateScreen(ImageScreenFragment.TAG);
    }

    /**
     * navigate on fragment
     *
     * @param tag represent navigation activity
     */
    private void navigateScreen(String tag) {
        Fragment frm = null;
        Bundle bundle;
        if (tag.equals(ImageScreenFragment.TAG)) {
            bundle = new Bundle();
            bundle.putInt(AppConstant.SELECTED_POSITION, mSelectedPos);
            bundle.putSerializable(AppConstant.GALLERY_IMAGE, (Serializable) mFileList);
            frm = ImageScreenFragment.getInstance(bundle);
        }
        navigateFragment(R.id.container, frm, true);
    }
}