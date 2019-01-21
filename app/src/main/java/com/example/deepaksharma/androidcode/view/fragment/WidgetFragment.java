package com.example.deepaksharma.androidcode.view.fragment;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.view.View;

import com.example.deepaksharma.androidcode.R;
import com.example.deepaksharma.androidcode.databinding.FragmentWidgetBinding;
import com.example.deepaksharma.androidcode.view.BaseFragment;

public class WidgetFragment extends BaseFragment {
    public static final String TAG = WidgetFragment.class.getSimpleName();
    private FragmentWidgetBinding mBinding;

    public static WidgetFragment getInstance(Bundle bundle) {
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
