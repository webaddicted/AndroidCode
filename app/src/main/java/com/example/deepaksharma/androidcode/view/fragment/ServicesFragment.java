package com.example.deepaksharma.androidcode.view.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.example.deepaksharma.androidcode.R;
import com.example.deepaksharma.androidcode.databinding.FragmentReceiverBinding;
import com.example.deepaksharma.androidcode.databinding.FragmentServicesBinding;
import com.example.deepaksharma.androidcode.utils.GlobalUtilities;
import com.example.deepaksharma.androidcode.view.base.BaseFragment;
import com.example.deepaksharma.androidcode.view.home.HomeActivity;

public class ServicesFragment extends BaseFragment {
    public static final String TAG = ServicesFragment.class.getSimpleName();
    private FragmentServicesBinding mBinding;

    public static ServicesFragment getInstance(Bundle bundle) {
        ServicesFragment fragment = new ServicesFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_services;
    }

    @Override
    protected void onViewsInitialized(ViewDataBinding binding, View view) {
        mBinding = (FragmentServicesBinding) binding;
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
