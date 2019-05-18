package com.example.deepaksharma.androidcode.view.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.deepaksharma.androidcode.model.eventBus.EventBusListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Created by Deepak Sharma on 15/1/19.
 */
public abstract class BaseDialogFragment extends DialogFragment implements View.OnClickListener {

    private ViewDataBinding mBinding;

    protected abstract int getLayoutId();

    protected abstract void onViewsInitialized(ViewDataBinding binding, View view);

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
        if (!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        onViewsInitialized(mBinding, view);
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void EventBusListener(EventBusListener eventBusListener) {
    }

    public void onClick(View v) {

    }
}
