package com.example.deepaksharma.androidcode.view.fragment;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.view.View;

import com.example.deepaksharma.androidcode.R;
import com.example.deepaksharma.androidcode.databinding.FragmentSelectMultipleFileBinding;
import com.example.deepaksharma.androidcode.global.ImageLoaderUtils;
import com.example.deepaksharma.androidcode.global.image.ImagePicker;
import com.example.deepaksharma.androidcode.view.base.BaseFragment;
import com.example.deepaksharma.androidcode.view.home.HomeActivity;

import java.io.File;
import java.util.List;

public class SelectMultipleFileFragment extends BaseFragment {
    public static final String TAG = SelectMultipleFileFragment.class.getSimpleName();

    private FragmentSelectMultipleFileBinding mBinding;

    public static SelectMultipleFileFragment getInstance(Bundle bundle) {
        SelectMultipleFileFragment fragment = new SelectMultipleFileFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_select_multiple_file;
    }

    @Override
    protected void onViewsInitialized(ViewDataBinding binding, View view) {
        mBinding = (FragmentSelectMultipleFileBinding) binding;
        init();
    }

    private void init() {
        mBinding.btnCaptureImg.setOnClickListener(this);
        mBinding.btnSelectImg.setOnClickListener(this);
        mBinding.btnSelectMultipleImg.setOnClickListener(this);
        mBinding.btnSelectVideo.setOnClickListener(this);
        mBinding.btnRecordVideo.setOnClickListener(this);
        mBinding.btnCompressVideo.setOnClickListener(this);
        mBinding.btnCropVideo.setOnClickListener(this);
        mBinding.btnCaptureRecord.setOnClickListener(this);

    }

    @Override
    public void onResume() {
        super.onResume();
        ((HomeActivity) getActivity()).showBackBtn();
        ((HomeActivity) getActivity()).setToolbarTitle(getResources().getString(R.string.select_multiple_image_title));
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.btn_capture_img:
                captureImage();
                break;
            case R.id.btn_select_img:
                selectImage();
                break;
            case R.id.btn_select_multiple_img:
                selectMultipleImage();
                break;
            case R.id.btn_select_video:
                selectVideo();
                break;
            case R.id.btn_record_video:
                recordVideo();
                break;
            case R.id.btn_compress_video:
                compressVideo();
                break;
            case R.id.btn_crop_video:
                cropVideo();
                break;
            case R.id.btn_capture_record:
                captureRecordVideo();
                break;

        }
    }

    private void captureImage() {
        ImagePicker.captureImage(getActivity(), new ImagePicker.ImagePickerListener() {
            @Override
            public void imagePath(List<File> filePath) {
                if (filePath != null && filePath.size() > 0) {
                    mBinding.txtNoOfItem.setText(String.valueOf(filePath.size()));
                    ImageLoaderUtils.showImageUsingGLIDE(filePath.get(0), mBinding.img, getImageLoader(1));
                }
            }
        });

    }

    private void selectImage() {
        ImagePicker.selectImage(getActivity(), new ImagePicker.ImagePickerListener() {
            @Override
            public void imagePath(List<File> filePath) {
                if (filePath != null && filePath.size() > 0) {
                    mBinding.txtNoOfItem.setText(String.valueOf(filePath.size()));
                    ImageLoaderUtils.showImageUsingGLIDE(filePath.get(0), mBinding.img, getImageLoader(1));
                }
            }
        });
    }

    private void selectMultipleImage() {
        ImagePicker.selectMultipleImage(getActivity(), new ImagePicker.ImagePickerListener() {
            @Override
            public void imagePath(List<File> filePath) {
                if (filePath != null && filePath.size() > 0) {
                    mBinding.txtNoOfItem.setText(String.valueOf(filePath.size()));
                    ImageLoaderUtils.showImageUsingGLIDE(filePath.get(0), mBinding.img, getImageLoader(1));
                }
            }
        });
    }

    private void selectVideo() {
        ImagePicker.selectVideo(getActivity(), new ImagePicker.ImagePickerListener() {
            @Override
            public void imagePath(List<File> filePath) {
                if (filePath != null && filePath.size() > 0) {
                    mBinding.txtNoOfItem.setText(String.valueOf(filePath.size()));
                    ImageLoaderUtils.showImageUsingGLIDE(filePath.get(0), mBinding.img, getImageLoader(1));
                }
            }
        });
    }

    private void recordVideo() {
        ImagePicker.recordVideo(getActivity(), new ImagePicker.ImagePickerListener() {
            @Override
            public void imagePath(List<File> filePath) {
                if (filePath != null && filePath.size() > 0) {
                    mBinding.txtNoOfItem.setText(String.valueOf(filePath.size()));
                    ImageLoaderUtils.showImageUsingGLIDE(filePath.get(0), mBinding.img, getImageLoader(1));
                }
            }
        });
    }

    private void compressVideo() {
    }

    private void cropVideo() {
    }

    private void captureRecordVideo() {
        ImagePicker.captureRecordImage(getActivity(), new ImagePicker.ImagePickerListener() {
            @Override
            public void imagePath(List<File> filePath) {
                if (filePath != null && filePath.size() > 0) {
                    mBinding.txtNoOfItem.setText(String.valueOf(filePath.size()));
                    ImageLoaderUtils.showImageUsingGLIDE(filePath.get(0), mBinding.img, getImageLoader(1));
                }
            }
        });
    }
}
