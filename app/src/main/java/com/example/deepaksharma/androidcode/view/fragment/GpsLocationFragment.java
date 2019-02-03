package com.example.deepaksharma.androidcode.view.fragment;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.view.View;

import com.example.deepaksharma.androidcode.R;
import com.example.deepaksharma.androidcode.databinding.FragmentGetLocationBinding;
import com.example.deepaksharma.androidcode.view.base.BaseFragment;
import com.example.deepaksharma.androidcode.view.home.HomeActivity;

public class GpsLocationFragment extends BaseFragment {
    public static final String TAG = GpsLocationFragment.class.getSimpleName();
    private FragmentGetLocationBinding mBinding;
    private String mLink;

    public static GpsLocationFragment getInstance(Bundle bundle) {
        GpsLocationFragment fragment = new GpsLocationFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_get_location;
    }

    @Override
    protected void onViewsInitialized(ViewDataBinding binding, View view) {
        mBinding = (FragmentGetLocationBinding) binding;
        init();
        clickListener();
    }

    private void init() {
    }


    private void clickListener() {
        mBinding.btnGetLocation.setOnClickListener(this);
        mBinding.btnGetLocationWithParam.setOnClickListener(this);
        mBinding.btnStopLocation.setOnClickListener(this);
        mBinding.btnLocationWithAddress.setOnClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        ((HomeActivity) getActivity()).showBackBtn();
        ((HomeActivity) getActivity()).setToolbarTitle(getResources().getString(R.string.current_location_title));
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.btn_get_location:
                getLocation();
                break;
            case R.id.btn_get_location_with_param:
                getLocation(2, 3, 10);
                break;
            case R.id.btn_location_with_address:
                getLocationWithAddress(2, 3, 10);
                break;
            case R.id.btn_stop_location:
                stopUpdateLocation();
                break;
        }
    }

}
