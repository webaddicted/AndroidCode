package com.example.deepaksharma.androidcode.view.fragment;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.view.View;

import com.example.deepaksharma.androidcode.R;
import com.example.deepaksharma.androidcode.databinding.FragmentGoogleMapBinding;
import com.example.deepaksharma.androidcode.databinding.FragmentServicesBinding;
import com.example.deepaksharma.androidcode.view.base.BaseFragment;
import com.example.deepaksharma.androidcode.view.home.HomeActivity;

public class GoogleMapFragment extends BaseFragment {
    public static final String TAG = GoogleMapFragment.class.getSimpleName();
    private FragmentGoogleMapBinding mBinding;

    public static GoogleMapFragment getInstance(Bundle bundle) {
        GoogleMapFragment fragment = new GoogleMapFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_google_map;
    }

    @Override
    protected void onViewsInitialized(ViewDataBinding binding, View view) {
        mBinding = (FragmentGoogleMapBinding) binding;
        init();
        clickListener();

    }

    private void init() {
    }

    private void clickListener() {
//        mBinding.btnBoot.setOnClickListener(this);
//        mBinding.btnSmsReceiver.setOnClickListener(this);
//        mBinding.btnSimChangeReceiver.setOnClickListener(this);
//        mBinding.btnReferralCode.setOnClickListener(this);
//        mBinding.btnNetworkStateChange.setOnClickListener(this);
//        mBinding.btnLocalReceiver.setOnClickListener(this);
//        mBinding.btnSwipeToDelete.setOnClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        ((HomeActivity) getActivity()).showBackBtn();
        ((HomeActivity) getActivity()).setToolbarTitle(getResources().getString(R.string.recycler_view_title));
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.btn_boot:
                break;
            case R.id.btn_sms_receiver:
                break;
            case R.id.btn_sim_change_receiver:
                break;
            case R.id.btn_referral_code:
                break;
            case R.id.btn_network_state_change:
                break;
            case R.id.btn_local_receiver:
                break;
        }
    }

}
