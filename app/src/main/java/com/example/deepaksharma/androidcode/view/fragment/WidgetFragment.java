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

public class WidgetFragment extends Fragment {
    private FragmentWidgetBinding mBinding;

    public static WidgetFragment newInstance(Bundle bundle) {
        WidgetFragment fragment = new WidgetFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       mBinding =  DataBindingUtil.bind(inflater.inflate(R.layout.fragment_widget, container, false));
       return mBinding.getRoot();
    }

}
