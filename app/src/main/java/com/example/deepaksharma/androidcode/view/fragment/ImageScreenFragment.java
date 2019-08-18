package com.example.deepaksharma.androidcode.view.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.databinding.ViewDataBinding;
import androidx.viewpager.widget.ViewPager;

import com.example.deepaksharma.androidcode.R;
import com.example.deepaksharma.androidcode.databinding.FragmentImageScreenBinding;
import com.example.deepaksharma.androidcode.global.constant.AppConstant;
import com.example.deepaksharma.androidcode.view.adapter.SlidingImageAdapter;
import com.example.deepaksharma.androidcode.view.base.BaseFragment;
import com.example.deepaksharma.androidcode.view.home.HomeActivity;

import java.io.File;
import java.util.List;

public class ImageScreenFragment extends BaseFragment {
    public static final String TAG = ImageScreenFragment.class.getSimpleName();
    private FragmentImageScreenBinding mBinding;
    private int mSelectedPos = 0;
    private List<File> mImageList;

    public static ImageScreenFragment getInstance(Bundle bundle) {
        ImageScreenFragment fragment = new ImageScreenFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_image_screen;
    }

    @Override
    protected void onViewsInitialized(ViewDataBinding binding, View view) {
        mBinding = (FragmentImageScreenBinding) binding;
        init();
    }

    private void init() {
        if (getArguments() != null && getArguments().containsKey(AppConstant.SELECTED_POSITION))
            mSelectedPos = getArguments().getInt(AppConstant.SELECTED_POSITION);
        if (getArguments() != null && getArguments().containsKey(AppConstant.GALLERY_IMAGE))
            mImageList = (List<File>) getArguments().getSerializable(AppConstant.GALLERY_IMAGE);
        setAdapter();
    }

    @Override
    public void onResume() {
        super.onResume();
        ((HomeActivity) getActivity()).showBackBtn();
        ((HomeActivity) getActivity()).setToolbarTitle(getResources().getString(R.string.device_image_title));
    }

    private void setAdapter() {
        if (mImageList != null) {
            mBinding.pager.setAdapter(new SlidingImageAdapter(mImageList));
            mBinding.pager.setCurrentItem(mSelectedPos);
        }
        mBinding.pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrolled(int pos, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int pos) {
            }
        });
    }

}