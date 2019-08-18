package com.example.deepaksharma.androidcode.view.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.databinding.ViewDataBinding;

import com.example.deepaksharma.androidcode.R;
import com.example.deepaksharma.androidcode.databinding.FragmentUiDesignBinding;
import com.example.deepaksharma.androidcode.view.base.BaseFragment;
import com.example.deepaksharma.androidcode.view.home.HomeActivity;

public class UiDesignFragment extends BaseFragment {
    public static final String TAG = UiDesignFragment.class.getSimpleName();
    private FragmentUiDesignBinding mBinding;
    public static UiDesignFragment getInstance(Bundle bundle) {
        UiDesignFragment fragment = new UiDesignFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_ui_design;
    }

    @Override
    protected void onViewsInitialized(ViewDataBinding binding, View view) {
        mBinding = (FragmentUiDesignBinding) binding;
        init();
        clickListener();
    }

    private void init() {

    }

    private void clickListener() {
        mBinding.btnCreatePdf.setOnClickListener(this);
        mBinding.btnSavePdf.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.btn_create_pdf:
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        ((HomeActivity) getActivity()).showBackBtn();
        ((HomeActivity) getActivity()).setToolbarTitle(getResources().getString(R.string.animation_title));
    }
}
