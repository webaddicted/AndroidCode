package com.example.deepaksharma.androidcode.view.loader;

import android.os.Bundle;
import android.view.View;

import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.deepaksharma.androidcode.R;
import com.example.deepaksharma.androidcode.databinding.DialogLoaderBinding;
import com.example.deepaksharma.androidcode.global.AppApplication;
import com.example.deepaksharma.androidcode.global.constant.ApiConstant;
import com.example.deepaksharma.androidcode.global.constant.AppConstant;
import com.example.deepaksharma.androidcode.global.constant.IntentKeyPool;
import com.example.deepaksharma.androidcode.view.base.BaseFragment;

public class LoaderFragment extends BaseFragment {
    public static final String TAG = LoaderFragment.class.getSimpleName();
    private DialogLoaderBinding mBinding;
    private int mLoaderPos = 0;

    public static LoaderFragment getInstance(Bundle bundle) {
        LoaderFragment fragment = new LoaderFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.dialog_loader;
    }

    @Override
    protected void onViewsInitialized(ViewDataBinding binding, View view) {
        mBinding = (DialogLoaderBinding) binding;
        init();
    }

    private void init() {
        if (getArguments() != null) {
            if (getArguments().containsKey(IntentKeyPool.LOADER_POSITION))
                mLoaderPos = getArguments().getInt(IntentKeyPool.LOADER_POSITION);
        }
        if (mLoaderPos == ApiConstant.LOADER_POS_CENTRE) {
            mBinding.pbCentre.setVisibility(View.VISIBLE);
            mBinding.pbBottom.setVisibility(View.GONE);
        } else if (mLoaderPos == ApiConstant.LOADER_POS_BOTTOM) {
            mBinding.pbCentre.setVisibility(View.GONE);
            mBinding.pbBottom.setVisibility(View.VISIBLE);
        }
        mBinding.linearLoader.setEnabled(false);
        mBinding.linearLoader.setClickable(false);
        mBinding.linearLoader.setFocusableInTouchMode(false);
        mBinding.linearLoader.setOnClickListener(v -> {
        });
    }

    public void navigateAddFrag(int loader_container, LoaderFragment mLoaderFrm) {
        FragmentManager fragmentManager = AppApplication.mSupportManager;
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(loader_container, mLoaderFrm);
//        if (isEnableBackStack)
//        fragmentTransaction.addToBackStack(mLoaderFrm.getClass().getSimpleName());
        fragmentTransaction.commitAllowingStateLoss();
    }
}
