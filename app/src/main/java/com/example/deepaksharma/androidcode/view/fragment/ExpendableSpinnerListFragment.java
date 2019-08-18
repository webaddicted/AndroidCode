package com.example.deepaksharma.androidcode.view.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.databinding.ViewDataBinding;

import com.example.deepaksharma.androidcode.R;
import com.example.deepaksharma.androidcode.databinding.FragmentExpendableListBinding;
import com.example.deepaksharma.androidcode.view.adapter.ExpendableListAdapter;
import com.example.deepaksharma.androidcode.view.adapter.SpinnerListAdapter;
import com.example.deepaksharma.androidcode.view.base.BaseFragment;
import com.example.deepaksharma.androidcode.view.home.HomeActivity;

import java.util.ArrayList;
import java.util.List;

public class ExpendableSpinnerListFragment extends BaseFragment {
    public static final String TAG = ExpendableSpinnerListFragment.class.getSimpleName();
    private FragmentExpendableListBinding mBinding;
    private ExpendableListAdapter mExpendAdapter;
    private SpinnerListAdapter mSpinnerAdapter;

    public static ExpendableSpinnerListFragment getInstance(Bundle bundle) {
        ExpendableSpinnerListFragment fragment = new ExpendableSpinnerListFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_expendable_list;
    }

    @Override
    protected void onViewsInitialized(ViewDataBinding binding, View view) {
        mBinding = (FragmentExpendableListBinding) binding;
        init();
        clickListener();
        getListBean();
        setExpendAdapter();
    }

    private void init() {
    }

    private void clickListener() {
        mBinding.btnExpendable.setOnClickListener(this);
        mBinding.btnSpinner.setOnClickListener(this);
    }

    private List<String> getListBean() {
        List<String> mList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            mList.add("task - # " + i);
        }
        return mList;
    }

    private void setExpendAdapter() {
        mSpinnerAdapter = null;
        mBinding.expandableListView.setVisibility(View.VISIBLE);
        mBinding.spinner.setVisibility(View.GONE);
        mExpendAdapter = new ExpendableListAdapter();
        mBinding.expandableListView.setAdapter(mExpendAdapter);
    }

    private void setSpinnerAdapter() {
        mExpendAdapter = null;
        mBinding.expandableListView.setVisibility(View.GONE);
        mBinding.spinner.setVisibility(View.VISIBLE);
        mSpinnerAdapter = new SpinnerListAdapter(getListBean());
        mBinding.spinner.setAdapter(mSpinnerAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        ((HomeActivity) getActivity()).showBackBtn();
        ((HomeActivity) getActivity()).setToolbarTitle(getResources().getString(R.string.expend_spinner_title));
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.btn_expendable:
                setExpendAdapter();
                break;
            case R.id.btn_spinner:
                setSpinnerAdapter();
                break;

        }
    }
}
