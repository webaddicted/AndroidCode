package com.example.deepaksharma.androidcode.view.adapter;

import android.databinding.ViewDataBinding;

import com.example.deepaksharma.androidcode.R;
import com.example.deepaksharma.androidcode.databinding.RowGridBinding;
import com.example.deepaksharma.androidcode.databinding.RowTextListBinding;
import com.example.deepaksharma.androidcode.view.base.BaseRecyclerViewAdapter;

import java.util.List;

public class CommonRecyclerViewAdapter extends BaseRecyclerViewAdapter {
    private final List<String> mListBean;

    public CommonRecyclerViewAdapter(List<String> listBean) {
        this.mListBean = listBean;
    }

    private class VIEW_TYPES {
        public static final int NORMAL = 1;
        public static final int FOOTER = 2;
    }

    @Override
    protected int getLayoutId(int viewType) {
        if (viewType == VIEW_TYPES.NORMAL) return R.layout.row_text_list;
        else return R.layout.row_grid;
    }

    @Override
    protected int getListSize() {
        return mListBean.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == getItemCount() - 1) return VIEW_TYPES.FOOTER;
        else return VIEW_TYPES.NORMAL;
    }

    @Override
    protected void onBindTo(ViewDataBinding rowBinding, int position) {
        if (rowBinding instanceof RowTextListBinding) {
            RowTextListBinding mRowBinding = (RowTextListBinding) rowBinding;
            mRowBinding.txtName.setText(mListBean.get(position));
        } else if (rowBinding instanceof RowGridBinding) {
            RowGridBinding mRowGridBinding = (RowGridBinding) rowBinding;
            mRowGridBinding.txtName.setText(mListBean.get(position));
        }
    }
}
