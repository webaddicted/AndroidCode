package com.example.deepaksharma.androidcode.view.fragment;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.example.deepaksharma.androidcode.R;
import com.example.deepaksharma.androidcode.databinding.FragmentRecylcerViewBinding;
import com.example.deepaksharma.androidcode.view.adapter.RecyclerGridAdapter;
import com.example.deepaksharma.androidcode.view.adapter.RecyclerListAdapter;
import com.example.deepaksharma.androidcode.view.adapter.CommonRecyclerViewAdapter;
import com.example.deepaksharma.androidcode.view.base.BaseFragment;
import com.example.deepaksharma.androidcode.view.home.HomeActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class RecyclerViewFragment extends BaseFragment {
    public static final String TAG = RecyclerViewFragment.class.getSimpleName();
    private FragmentRecylcerViewBinding mBinding;
    private RecyclerListAdapter mListAdapter;
    private RecyclerGridAdapter mGridAdapter;
    private CommonRecyclerViewAdapter mCommonAdapter;

    public static RecyclerViewFragment getInstance(Bundle bundle) {
        RecyclerViewFragment fragment = new RecyclerViewFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_recylcer_view;
    }

    @Override
    protected void onViewsInitialized(ViewDataBinding binding, View view) {
        mBinding = (FragmentRecylcerViewBinding) binding;
        init();
        clickListener();
        getListBean();
//        setListAdapter();

    }

    private void init() {
    }

    private void clickListener() {
        mBinding.btnGrid.setOnClickListener(this);
        mBinding.btnList.setOnClickListener(this);
        mBinding.btnCommon.setOnClickListener(this);
        mBinding.edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String text = mBinding.edtSearch.getText().toString().toLowerCase(Locale.getDefault());
                if (mListAdapter != null) mListAdapter.filter(text);
                if (mGridAdapter != null) mGridAdapter.filter(text);
//                mHomeAdapter.getFilter().filter(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }

    private List<String> getListBean() {
        List<String> mList = new ArrayList<>();
        for (int i = 0; i < 50; i++) mList.add("task - # " + i);
        return mList;
    }

    private void setCommonAdapter() {
        mGridAdapter = null;
        mCommonAdapter = new CommonRecyclerViewAdapter(getListBean());
        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        mBinding.recyclerView.setAdapter(mCommonAdapter);
    }


    private void setListAdapter() {
        mGridAdapter = null;
        mListAdapter = new RecyclerListAdapter(RecyclerViewFragment.this, getListBean());
        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        mBinding.recyclerView.setAdapter(mListAdapter);
    }

    private void setGridAdapter() {
        mListAdapter = null;
        mGridAdapter = new RecyclerGridAdapter(RecyclerViewFragment.this, getListBean());
        mBinding.recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        mBinding.recyclerView.setItemAnimator(new DefaultItemAnimator());
        mBinding.recyclerView.setAdapter(mGridAdapter);
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
            case R.id.btn_list:
                setListAdapter();
                break;
            case R.id.btn_grid:
                setGridAdapter();
                break;
            case R.id.btn_common:
                setCommonAdapter();
                break;
        }
    }
}
