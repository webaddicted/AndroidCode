package com.example.deepaksharma.androidcode.view.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.example.deepaksharma.androidcode.R;
import com.example.deepaksharma.androidcode.databinding.FragmentReceiverBinding;
import com.example.deepaksharma.androidcode.databinding.FragmentRecylcerViewBinding;
import com.example.deepaksharma.androidcode.utils.GlobalUtilities;
import com.example.deepaksharma.androidcode.view.adapter.CommonRecyclerViewAdapter;
import com.example.deepaksharma.androidcode.view.adapter.ElaborateRecyclerAdapter;
import com.example.deepaksharma.androidcode.view.adapter.EndLessScrollAdapter;
import com.example.deepaksharma.androidcode.view.adapter.RecyclerGridAdapter;
import com.example.deepaksharma.androidcode.view.adapter.RecyclerListAdapter;
import com.example.deepaksharma.androidcode.view.adapter.StaggeredGridAdapter;
import com.example.deepaksharma.androidcode.view.adapter.SwipToDeleteAdapter;
import com.example.deepaksharma.androidcode.view.base.BaseFragment;
import com.example.deepaksharma.androidcode.view.home.HomeActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ReceiverFragment extends BaseFragment {
    public static final String TAG = ReceiverFragment.class.getSimpleName();
    private FragmentReceiverBinding mBinding;
    static final String ACTION_STARTED = "com.example.android.supportv4.STARTED";
    static final String ACTION_UPDATE = "com.example.android.supportv4.UPDATE";
    static final String ACTION_STOPPED = "com.example.android.supportv4.STOPPED";
    //    private BroadcastReceiver mReceiver;
//    private LocalBroadcastManager mLocalBroadcastManager;
    private IntentFilter mFilter;

    public static ReceiverFragment getInstance(Bundle bundle) {
        ReceiverFragment fragment = new ReceiverFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_receiver;
    }

    @Override
    protected void onViewsInitialized(ViewDataBinding binding, View view) {
        mBinding = (FragmentReceiverBinding) binding;
        init();
        clickListener();

    }

    private void init() {
        mFilter = new IntentFilter();
        mFilter.addAction(ACTION_STARTED);
        mFilter.addAction(ACTION_UPDATE);
        mFilter.addAction(ACTION_STOPPED);
//        getActivity().sendBroadcast(mFilter);
//        mLocalBroadcastManager.registerReceiver(mReceiver, mFilter);
        getActivity().registerReceiver(mReceiver, mFilter);
    }

    private void clickListener() {
        mBinding.btnBoot.setOnClickListener(this);
        mBinding.btnSmsReceiver.setOnClickListener(this);
        mBinding.btnSimChangeReceiver.setOnClickListener(this);
        mBinding.btnReferralCode.setOnClickListener(this);
        mBinding.btnNetworkStateChange.setOnClickListener(this);
        mBinding.btnLocalReceiver.setOnClickListener(this);
//        mBinding.btnSwipeToDelete.setOnClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        ((HomeActivity) getActivity()).showBackBtn();
        ((HomeActivity) getActivity()).setToolbarTitle(getResources().getString(R.string.recycler_view_title));
        getActivity().registerReceiver(mReceiver, mFilter);
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
                localReceiver();
//                startService(new Intent(getActivity(), LocalService.class));
                break;
        }
    }

    private void localReceiver() {
        new Handler().postDelayed(() -> {
            Intent broadcastIntent = new Intent();
            broadcastIntent.setAction(ACTION_UPDATE);
            broadcastIntent.putExtra("Data", "Broadcast Data");
            getActivity().sendBroadcast(broadcastIntent);
        }, 3000);

    }

    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(ACTION_STARTED)) {
                GlobalUtilities.showToast("STARTED");
            } else if (intent.getAction().equals(ACTION_UPDATE)) {
                GlobalUtilities.showToast("Got update: " + intent.getIntExtra("value", 0));
            } else if (intent.getAction().equals(ACTION_STOPPED)) {
                GlobalUtilities.showToast("STOPPED");
            }
        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
//        mLocalBroadcastManager.unregisterReceiver(mReceiver);
        getActivity().unregisterReceiver(mReceiver);

    }
}
