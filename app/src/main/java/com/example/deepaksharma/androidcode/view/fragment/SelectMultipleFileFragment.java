package com.example.deepaksharma.androidcode.view.fragment;

import android.content.Intent;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.view.View;

import com.example.deepaksharma.androidcode.R;
import com.example.deepaksharma.androidcode.databinding.FragmentSelectMultipleFileBinding;
import com.example.deepaksharma.androidcode.databinding.FragmentWidgetBinding;
import com.example.deepaksharma.androidcode.global.constant.AppConstant;
import com.example.deepaksharma.androidcode.view.BaseFragment;
import com.example.deepaksharma.androidcode.view.home.HomeActivity;

public class SelectMultipleFileFragment extends BaseFragment implements View.OnClickListener {
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
        mBinding.btnSelectImg.setOnClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        ((HomeActivity) getActivity()).showBackBtn();
        ((HomeActivity) getActivity()).setToolbarTitle(getResources().getString(R.string.select_multiple_image_title));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_select_img:
                selectMultipleImage();
                break;
        }
    }

    private void selectMultipleImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select Picture"), AppConstant.PICK_IMAGE_MULTIPLE);
    }

}
