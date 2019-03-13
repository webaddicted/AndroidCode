package com.example.deepaksharma.androidcode.view.adapter;

import android.databinding.ViewDataBinding;

import com.example.deepaksharma.androidcode.R;
import com.example.deepaksharma.androidcode.databinding.RowTextListBinding;
import com.example.deepaksharma.androidcode.view.base.BaseRecyclerViewAdapter;

import java.util.List;

public class CommonRecyclerViewAdapter extends BaseRecyclerViewAdapter {
    private final List<String> mListBean;

    public CommonRecyclerViewAdapter(List<String> listBean) {
        this.mListBean = listBean;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.row_text_list;
    }

    @Override
    protected int getListSize() {
        return mListBean.size();
    }

    @Override
    protected void onBindTo(ViewDataBinding rowBinding, int adapterPosition, int position) {
        RowTextListBinding mRowBinding = (RowTextListBinding) rowBinding;
        mRowBinding.txtName.setText(mListBean.get(adapterPosition));
    }

}
