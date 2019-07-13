package com.example.deepaksharma.androidcode.view.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.ViewDataBinding;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.example.deepaksharma.androidcode.R;
import com.example.deepaksharma.androidcode.databinding.FragmentGetLocationBinding;
import com.example.deepaksharma.androidcode.view.adapter.LocationAdapter;
import com.example.deepaksharma.androidcode.view.base.BaseFragment;
import com.example.deepaksharma.androidcode.view.home.HomeActivity;
import com.example.deepaksharma.androidcode.viewModel.home.HomeViewModel;

import java.util.ArrayList;
import java.util.List;

public class GpsLocationFragment extends BaseFragment {
    public static final String TAG = GpsLocationFragment.class.getSimpleName();
    private FragmentGetLocationBinding mBinding;
    private HomeViewModel mHomeViewModel;
    private List<Location> mLocationBean;
    private LocationAdapter mLocationAdapter;

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
        mHomeViewModel = ViewModelProviders.of(getActivity()).get(HomeViewModel.class);
        init();
        clickListener();
        setAdapter();
    }

    private void init() {
        mHomeViewModel.mCurrentLocation.observe(this, location -> {
            if (location != null && location.getLatitude() > 0 && location.getLongitude() > 0) {
                if (mLocationBean == null) mLocationBean = new ArrayList<>();
                mLocationBean.add(0,location);
                mLocationAdapter.notifyUi(mLocationBean);
            }
        });
    }


    private void clickListener() {
        mBinding.btnGetLocation.setOnClickListener(this);
        mBinding.btnGetLocationWithParam.setOnClickListener(this);
        mBinding.btnStopLocation.setOnClickListener(this);
        mBinding.btnLocationWithAddress.setOnClickListener(this);
    }

    private void setAdapter() {
        mLocationAdapter = new LocationAdapter(mLocationBean);
        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        mBinding.recyclerView.setAdapter(mLocationAdapter);
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
                getLocation(2, 3, 0);
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
