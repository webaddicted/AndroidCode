package com.example.deepaksharma.androidcode.view.fragment;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.os.Build;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;

import com.example.deepaksharma.androidcode.R;
import com.example.deepaksharma.androidcode.databinding.FragmentPhoneDetailBinding;
import com.example.deepaksharma.androidcode.view.base.BaseFragment;
import com.example.deepaksharma.androidcode.view.home.HomeActivity;

public class GetPhoneDetailFragment extends BaseFragment {
    public static final String TAG = GetPhoneDetailFragment.class.getSimpleName();
    private FragmentPhoneDetailBinding mBinding;

    public static GetPhoneDetailFragment getInstance(Bundle bundle) {
        GetPhoneDetailFragment fragment = new GetPhoneDetailFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_phone_detail;
    }

    @Override
    protected void onViewsInitialized(ViewDataBinding binding, View view) {
        mBinding = (FragmentPhoneDetailBinding) binding;
        init();
    }

    private void init() {
        TelephonyManager telephonyManager = (TelephonyManager) getActivity().getSystemService(Context.TELEPHONY_SERVICE);
        try {
            String phoneNumber = telephonyManager.getLine1Number();
            String simSerialNumber = telephonyManager.getSimSerialNumber();
            String cellLocation = telephonyManager.getCellLocation() + "";
            String simOperater = telephonyManager.getSimOperator();
            String softwareVersion = telephonyManager.getDeviceSoftwareVersion();
            String manufacture = Build.MANUFACTURER;
            String device = Build.DEVICE;
            String product = Build.PRODUCT;
            String brand = Build.BRAND;
            String serial = Build.SERIAL;
            String model = Build.MODEL;
            String oper = telephonyManager.getSimOperatorName();
            String IMEI = telephonyManager.getDeviceId();
            mBinding.txtPhoneInfo.setText("phone Number - " + phoneNumber +
                    "\nsimSerialNumber - " + simSerialNumber +
                    "\ncellLocation - " + cellLocation +
                    "\nsimOperater - " + simOperater +
                    "\nsoftwareVersion - " + softwareVersion +
                    "\nmanufacture - " + manufacture +
                    "\ndevice - " + device +
                    "\nproduct - " + product +
                    "\nbrand - " + brand +
                    "\nserial - " + serial +
                    "\nmodel - " + model +
                    "\noperrator - " + oper +
                    "\nIMEI - " + IMEI);
        } catch (Exception e) {
            Log.d(TAG, "init: " + e.toString());
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        ((HomeActivity) getActivity()).showBackBtn();
        ((HomeActivity) getActivity()).setToolbarTitle(getResources().getString(R.string.device_info_title));
    }
}
