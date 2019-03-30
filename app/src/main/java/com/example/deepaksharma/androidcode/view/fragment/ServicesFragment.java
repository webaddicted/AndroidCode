package com.example.deepaksharma.androidcode.view.fragment;

import android.Manifest;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.example.deepaksharma.androidcode.R;
import com.example.deepaksharma.androidcode.databinding.FragmentReceiverBinding;
import com.example.deepaksharma.androidcode.databinding.FragmentServicesBinding;
import com.example.deepaksharma.androidcode.global.PermissionsHandler;
import com.example.deepaksharma.androidcode.global.StorageInfoUtils;
import com.example.deepaksharma.androidcode.model.NetworkListenerBean;
import com.example.deepaksharma.androidcode.model.eventBus.EventAllImageListener;
import com.example.deepaksharma.androidcode.model.eventBus.EventCallLogListener;
import com.example.deepaksharma.androidcode.model.eventBus.EventContactListener;
import com.example.deepaksharma.androidcode.model.eventBus.EventSmsListener;
import com.example.deepaksharma.androidcode.services.CallLogService;
import com.example.deepaksharma.androidcode.services.CapturePhotoService;
import com.example.deepaksharma.androidcode.services.ContactService;
import com.example.deepaksharma.androidcode.services.GetAllImageService;
import com.example.deepaksharma.androidcode.services.SmsService;
import com.example.deepaksharma.androidcode.utils.GlobalUtilities;
import com.example.deepaksharma.androidcode.view.adapter.CallLogAdapter;
import com.example.deepaksharma.androidcode.view.adapter.ContactAdapter;
import com.example.deepaksharma.androidcode.view.adapter.DeviceImageRecyclerViewAdapter;
import com.example.deepaksharma.androidcode.view.adapter.SmsAdapter;
import com.example.deepaksharma.androidcode.view.base.BaseFragment;
import com.example.deepaksharma.androidcode.view.home.HomeActivity;

import org.greenrobot.eventbus.Subscribe;

import java.io.File;
import java.util.Calendar;
import java.util.List;

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
        mBinding.btnCaptureImage.setOnClickListener(this);
        mBinding.btnReadImage.setOnClickListener(this);
        mBinding.btnReadPhoneContact.setOnClickListener(this);
        mBinding.btnReadSms.setOnClickListener(this);
        mBinding.btnReadCallLog.setOnClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        ((HomeActivity) getActivity()).showBackBtn();
        ((HomeActivity) getActivity()).setToolbarTitle(getResources().getString(R.string.service_fragment_title));
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.btn_capture_image:
                captureImage();
                break;
            case R.id.btn_read_image:
                readImage();
                break;
            case R.id.btn_read_phone_contact:
                readPhoneContact();
                break;
            case R.id.btn_read_sms:
                readSms();
                break;
            case R.id.btn_read_call_log:
                readCallLog();
                break;
        }
    }

    private void captureImage() {
        if (PermissionsHandler.checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            Intent service = new Intent(getActivity(), CapturePhotoService.class);
            getActivity().startService(service);
        }
    }

    private void readImage() {
        if (PermissionsHandler.checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            GlobalUtilities.handleUI(getActivity(), mBinding.loader.parentLoader, true);
            Intent service = new Intent(getActivity(), GetAllImageService.class);
            getActivity().startService(service);
        }
    }

    private void readPhoneContact() {
        if (PermissionsHandler.checkPermission(Manifest.permission.READ_CONTACTS)) {
            GlobalUtilities.handleUI(getActivity(), mBinding.loader.parentLoader, true);
            Intent service = new Intent(getActivity(), ContactService.class);
            getActivity().startService(service);
        }
    }

    private void readSms() {
        if (PermissionsHandler.checkPermission(Manifest.permission.READ_SMS)) {
            GlobalUtilities.handleUI(getActivity(), mBinding.loader.parentLoader, true);
            Intent service = new Intent(getActivity(), SmsService.class);
            getActivity().startService(service);
        }
    }

    private void readCallLog() {
        if (PermissionsHandler.checkPermission(Manifest.permission.READ_CALL_LOG)) {
            GlobalUtilities.handleUI(getActivity(), mBinding.loader.parentLoader, true);
            Intent service = new Intent(getActivity(), CallLogService.class);
            getActivity().startService(service);
        }
    }

    @Subscribe
    public void AllImageListener(EventAllImageListener eventAllImageListener) {
        if (eventAllImageListener != null && eventAllImageListener.getmFile() != null && eventAllImageListener.getmFile().size() > 0) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    DeviceImageRecyclerViewAdapter mAdapter = new DeviceImageRecyclerViewAdapter(null, eventAllImageListener.getmFile());
                    mBinding.rv.setLayoutManager(new GridLayoutManager(getActivity(), 2));
                    mBinding.rv.setAdapter(mAdapter);
                    GlobalUtilities.handleUI(getActivity(), mBinding.loader.parentLoader, false);
                }
            });
        }
    }

    @Subscribe
    public void ReadContactListener(EventContactListener eventContactListener) {
        if (eventContactListener != null && eventContactListener.getmAllContact() != null && eventContactListener.getmAllContact().size() > 0) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    ContactAdapter mAdapter = new ContactAdapter(null, eventContactListener.getmAllContact());
                    mBinding.rv.setLayoutManager(new LinearLayoutManager(getActivity()));
                    mBinding.rv.setAdapter(mAdapter);
                    GlobalUtilities.handleUI(getActivity(), mBinding.loader.parentLoader, false);
                }
            });
        }
    }

    @Subscribe
    public void ReadSmsListener(EventSmsListener eventSmsListener) {
        if (eventSmsListener != null && eventSmsListener.getmAllSms() != null && eventSmsListener.getmAllSms().size() > 0) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    SmsAdapter mAdapter = new SmsAdapter(null, eventSmsListener.getmAllSms());
                    mBinding.rv.setLayoutManager(new LinearLayoutManager(getActivity()));
                    mBinding.rv.setAdapter(mAdapter);
                    GlobalUtilities.handleUI(getActivity(), mBinding.loader.parentLoader, false);
                }
            });
        }
    }

    @Subscribe
    public void CallLogListener(EventCallLogListener eventCallLogListener) {
        if (eventCallLogListener != null && eventCallLogListener.getmCallLog() != null && eventCallLogListener.getmCallLog().size() > 0) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    CallLogAdapter mAdapter = new CallLogAdapter(null, eventCallLogListener.getmCallLog());
                    mBinding.rv.setLayoutManager(new LinearLayoutManager(getActivity()));
                    mBinding.rv.setAdapter(mAdapter);
                    GlobalUtilities.handleUI(getActivity(), mBinding.loader.parentLoader, false);
                }
            });
        }
    }

}
