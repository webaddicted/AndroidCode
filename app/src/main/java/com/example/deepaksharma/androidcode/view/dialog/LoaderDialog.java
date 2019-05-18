package com.example.deepaksharma.androidcode.view.dialog;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.view.View;

import com.example.deepaksharma.androidcode.R;
import com.example.deepaksharma.androidcode.databinding.DialogLoaderBinding;
import com.example.deepaksharma.androidcode.global.DialogUtil;
import com.example.deepaksharma.androidcode.view.base.BaseDialogFragment;

public class LoaderDialog extends BaseDialogFragment {
    public static final String TAG = LoaderDialog.class.getSimpleName();
    private DialogLoaderBinding mBinding;

    public static LoaderDialog getInstance(Bundle bundle) {
        LoaderDialog fragment = new LoaderDialog();
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
        mBinding.linearLoader.setClickable(false);
        mBinding.linearLoader.setEnabled(false);
    }

    @Override
    public void onResume() {
        super.onResume();
        DialogUtil.fullScreenDialogBounds(getActivity(), getDialog());
//        DialogUtil.modifyDialogBounds(getActivity(), getDialog());

    }
//    public void showFragment(final FragmentManager fragmentManager) {
//        FragmentTransaction transaction = AppApplication.getActivityInstance().getFragmentManager().beginTransaction();
//        transaction.add(Window.ID_ANDROID_CONTENT, LoaderDialog.this, LoaderDialog.TAG);
//        transaction.addToBackStack(LoaderDialog.TAG);
//        transaction.commitAllowingStateLoss();
//    }
}
