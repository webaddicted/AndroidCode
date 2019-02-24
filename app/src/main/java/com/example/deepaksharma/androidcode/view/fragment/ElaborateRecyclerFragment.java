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
import com.example.deepaksharma.androidcode.databinding.FragmentElaborateRecylcerBinding;
import com.example.deepaksharma.androidcode.databinding.FragmentRecylcerViewBinding;
import com.example.deepaksharma.androidcode.view.adapter.ElaborateRecyclerAdapter;
import com.example.deepaksharma.androidcode.view.adapter.RecyclerGridAdapter;
import com.example.deepaksharma.androidcode.view.adapter.RecyclerListAdapter;
import com.example.deepaksharma.androidcode.view.base.BaseFragment;
import com.example.deepaksharma.androidcode.view.home.HomeActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ElaborateRecyclerFragment extends BaseFragment {
    public static final String TAG = ElaborateRecyclerFragment.class.getSimpleName();
    private FragmentElaborateRecylcerBinding mBinding;
    private ElaborateRecyclerAdapter mElaboarteAdapter;

    public static ElaborateRecyclerFragment getInstance(Bundle bundle) {
        ElaborateRecyclerFragment fragment = new ElaborateRecyclerFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_elaborate_recylcer;
    }

    @Override
    protected void onViewsInitialized(ViewDataBinding binding, View view) {
        mBinding = (FragmentElaborateRecylcerBinding) binding;
        init();
        clickListener();
        getListBean();
        setListAdapter();
    }

    private void init() {
    }

    private void clickListener() {
        mBinding.btnGrid.setOnClickListener(this);
        mBinding.btnList.setOnClickListener(this);
        mBinding.edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String text = mBinding.edtSearch.getText().toString().toLowerCase(Locale.getDefault());
                if (mElaboarteAdapter != null) mElaboarteAdapter.filter(text);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private List<String> getListBean() {
        List<String> mList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            mList.add("task - # " + i);
        }
        return mList;
    }

    private void setListAdapter() {
//        mGridAdapter = null;
        mElaboarteAdapter = new ElaborateRecyclerAdapter(ElaborateRecyclerFragment.this, getListBean());
        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        mBinding.recyclerView.setAdapter(mElaboarteAdapter);
    }

    private void setGridAdapter() {
//        mListAdapter = null;
//        mGridAdapter = new RecyclerGridAdapter(ElaborateRecyclerFragment.this, getListBean());
//        mBinding.recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
//        mBinding.recyclerView.setItemAnimator(new DefaultItemAnimator());
//        mBinding.recyclerView.setAdapter(mGridAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        ((HomeActivity) getActivity()).showBackBtn();
        ((HomeActivity) getActivity()).setToolbarTitle(getResources().getString(R.string.elaborate_recycler_view_title));
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

        }
    }
}
