package com.example.deepaksharma.androidcode.view.adapter;

import android.content.Context;
import android.databinding.ViewDataBinding;

import com.example.deepaksharma.androidcode.R;
import com.example.deepaksharma.androidcode.databinding.RowGridBinding;
import com.example.deepaksharma.androidcode.global.AppApplication;
import com.example.deepaksharma.androidcode.global.ImageLoaderUtils;
import com.example.deepaksharma.androidcode.view.base.BaseEndLessRecyclerViewAdapter;
import com.example.deepaksharma.androidcode.view.fragment.RecyclerViewFragment;

import java.util.List;

public class StaggeredGridAdapter extends BaseEndLessRecyclerViewAdapter {
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
        return mListBean.size();
    }

    @Override
    protected boolean isEndLessScroll() {
        return false;
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
