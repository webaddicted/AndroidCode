package com.example.deepaksharma.androidcode.view.adapter;

import android.content.Context;

import androidx.databinding.ViewDataBinding;

import com.example.deepaksharma.androidcode.R;
import com.example.deepaksharma.androidcode.databinding.RowGridBinding;
import com.example.deepaksharma.androidcode.global.AppApplication;
import com.example.deepaksharma.androidcode.global.ImageLoaderUtils;
import com.example.deepaksharma.androidcode.view.base.BaseRecyclerViewAdapter;
import com.example.deepaksharma.androidcode.view.fragment.RecyclerViewFragment;

import java.util.List;

public class StaggeredGridAdapter extends BaseRecyclerViewAdapter {
    private List<String> mListBean;
    private RecyclerViewFragment mRecyclerViewFragment;
    private Context mContext = AppApplication.getInstance();
    public StaggeredGridAdapter(RecyclerViewFragment recyclerViewFragment, List<String> mEndLessList) {
        this.mRecyclerViewFragment = recyclerViewFragment;
        this.mListBean = mEndLessList;
    }

    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.row_grid;
    }

    @Override
    protected int getListSize() {
        return (mListBean == null || mListBean.size() == 0) ? 0 : mListBean.size();
    }

    @Override
    protected void onBindTo(ViewDataBinding rowBinding, int position) {
        if (rowBinding instanceof RowGridBinding) {
            RowGridBinding mRowBinding = (RowGridBinding) rowBinding;
            ImageLoaderUtils.showImageUsingGLIDE(mListBean.get(position),mRowBinding.img,getPlaceHolder(1));
//            mRowBinding.txtName.setText(mListBean.get(position));
        }
    }
}
