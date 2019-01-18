package com.example.deepaksharma.androidcode.view.fragment;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.deepaksharma.androidcode.R;
import com.example.deepaksharma.androidcode.databinding.FragmentWidgetBinding;
import com.example.deepaksharma.androidcode.view.BaseFragment;

public class WidgetFragment extends BaseFragment {
    private FragmentWidgetBinding mBinding;

    public static WidgetFragment newInstance(Bundle bundle) {
        WidgetFragment fragment = new WidgetFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_widget;
    }

    @Override
    protected void onViewsInitialized(ViewDataBinding binding, View view) {
        mBinding = (FragmentWidgetBinding) binding;
    }


}
