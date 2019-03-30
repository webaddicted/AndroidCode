package com.example.deepaksharma.androidcode.view.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.ViewDataBinding;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;

import com.example.deepaksharma.androidcode.R;
import com.example.deepaksharma.androidcode.databinding.FragmentShareBinding;
import com.example.deepaksharma.androidcode.databinding.FragmentViewPagerTabBinding;
import com.example.deepaksharma.androidcode.global.FileUtils;
import com.example.deepaksharma.androidcode.global.image.ImagePicker;
import com.example.deepaksharma.androidcode.utils.GlobalUtilities;
import com.example.deepaksharma.androidcode.view.base.BaseFragment;
import com.example.deepaksharma.androidcode.view.home.HomeActivity;
import com.example.deepaksharma.androidcode.viewModel.home.HomeViewModel;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.List;

public class ViewPagerTabFragment extends BaseFragment {
    public static final String TAG = ViewPagerTabFragment.class.getSimpleName();
    private FragmentViewPagerTabBinding mBinding;
    private HomeViewModel mHomeViewModel;

    public static ViewPagerTabFragment getInstance(Bundle bundle) {
        ViewPagerTabFragment fragment = new ViewPagerTabFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_view_pager_tab;
    }

    @Override
    protected void onViewsInitialized(ViewDataBinding binding, View view) {
        mBinding = (FragmentViewPagerTabBinding) binding;
        mHomeViewModel = ViewModelProviders.of(getActivity()).get(HomeViewModel.class);
        clickListener();
    }

    private void clickListener() {
        mBinding.btnShareText.setOnClickListener(this);
        mBinding.btnShareImage.setOnClickListener(this);
        mBinding.btnShareImgText.setOnClickListener(this);
        mBinding.btnShareLocalImage.setOnClickListener(this);
        mBinding.btnSendEmail.setOnClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        ((HomeActivity) getActivity()).showBackBtn();
        ((HomeActivity) getActivity()).setToolbarTitle(getResources().getString(R.string.view_pager_tab_title));
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.btn_share_text:
                shareText();
                break;
            case R.id.btn_share_image:
                checkPermission(false);
                break;
            case R.id.btn_share_img_text:
                checkPermission(true);
                break;
            case R.id.btn_share_local_image:
                shareLocalImage();
                break;
            case R.id.btn_send_email:
                sendEmail();
                break;
        }
    }

    private void shareText() {
        String shareBody = "Here is the share content body";
        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject Here");
        sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
        startActivity(Intent.createChooser(sharingIntent, getResources().getString(R.string.share_text)));
    }

    private void shareImage(File file) {
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("image/jpeg");
        share.putExtra(Intent.EXTRA_STREAM, Uri.parse(file.toString()));
        startActivity(Intent.createChooser(share, getResources().getString(R.string.share_image)));
    }

    private void checkPermission(boolean isShareImgText) {
        checkStoragePermission();
        mHomeViewModel.mIsPermissionGranted.observe(this, permissionGranted -> {
            if (permissionGranted) selectImage(isShareImgText);
            else GlobalUtilities.showToast(getResources().getString(R.string.permission_require));
        });
    }

    private void selectImage(boolean isShareImgText) {
        ImagePicker.selectImage(getActivity(), new ImagePicker.ImagePickerListener() {
            @Override
            public void imagePath(List<File> filePath) {
                if (isShareImgText) shareImageText(filePath.get(0));
                shareImage(filePath.get(0));
            }
        });
    }

    private void shareImageText(File file) {
        Bitmap b = FileUtils.getBitmapFromFile(file);
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("image/png");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        b.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(getActivity().getContentResolver(),
                b, "Title", null);
        Uri imageUri = Uri.parse(path);
        share.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.app_name));
        share.putExtra(Intent.EXTRA_STREAM, imageUri);
        share.putExtra(Intent.EXTRA_TEXT, getString(R.string.REFERAL_TITLE) + "\nhttps://play.google.com/store/apps/details?id=com.solution.mircroprixs.tesseract&referrer=" + "TESSPOWER");
        startActivity(Intent.createChooser(share, "Select"));
    }

    private void shareLocalImage() {
        Bitmap b = BitmapFactory.decodeResource(getResources(), R.drawable.logo);
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("image/png");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        b.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(getActivity().getContentResolver(),
                b, "Title", null);
        Uri imageUri = Uri.parse(path);
        share.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.app_name));
        share.putExtra(Intent.EXTRA_STREAM, imageUri);
        share.putExtra(Intent.EXTRA_TEXT, getString(R.string.REFERAL_TITLE) + "\nhttps://play.google.com/store/apps/details?id=com.solution.mircroprixs.tesseract&referrer=" + "TESSPOWER");
        startActivity(Intent.createChooser(share, "Select"));
    }

    private void sendEmail() {
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto", "abc@gmail.com", null));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Body");
        startActivity(Intent.createChooser(emailIntent, "Send email..."));
    }
}