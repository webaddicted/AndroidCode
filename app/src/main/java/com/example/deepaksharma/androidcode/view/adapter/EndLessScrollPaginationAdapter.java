package com.example.deepaksharma.androidcode.view.adapter;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.support.v4.app.Fragment;

import com.example.deepaksharma.androidcode.R;
import com.example.deepaksharma.androidcode.databinding.RowTextListBinding;
import com.example.deepaksharma.androidcode.global.AppApplication;
import com.example.deepaksharma.androidcode.view.base.BaseEndLessRecyclerViewAdapter;
import com.example.deepaksharma.androidcode.view.fragment.ApiFragment;
import com.example.deepaksharma.androidcode.view.fragment.PaginationFragment;

import java.util.List;

public class EndLessScrollPaginationAdapter extends BaseEndLessRecyclerViewAdapter {
    private final List<String> mListBean;
    private final Fragment mRecyclerViewFragment;
    private Context mContext = AppApplication.getInstance();

    public EndLessScrollPaginationAdapter(Fragment recyclerViewFragment, List<String> listBean) {
        this.mRecyclerViewFragment = recyclerViewFragment;
        this.mListBean = listBean;
    }

    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.row_text_list;
    }

    @Override
    protected int getListSize() {
        return (mListBean == null || mListBean.size() == 0) ? 0 : mListBean.size();
    }

    @Override
    protected boolean isEndLessScroll() {
        return true;
    }


    @Override
    protected void onBindTo(ViewDataBinding rowBinding, int position) {
        if (rowBinding instanceof RowTextListBinding) {
            RowTextListBinding mRowBinding = (RowTextListBinding) rowBinding;
            mRowBinding.txtName.setText(mListBean.get(position));
        }
    }

    @Override
    protected void loadData() {
        super.loadData();
        if (mRecyclerViewFragment instanceof PaginationFragment)
            ((PaginationFragment) mRecyclerViewFragment).loadNewItems();
    }
}
