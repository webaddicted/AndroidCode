package com.example.deepaksharma.androidcode.view.adapter;

import android.databinding.ViewDataBinding;
import android.view.View;

import com.example.deepaksharma.androidcode.R;
import com.example.deepaksharma.androidcode.databinding.RowGridBinding;
import com.example.deepaksharma.androidcode.global.ImageLoaderUtils;
import com.example.deepaksharma.androidcode.view.base.BaseEndLessRecyclerViewAdapter;
import com.example.deepaksharma.androidcode.view.fragment.ImageFragment;

import java.io.File;
import java.util.List;

public class DeviceImageRecyclerViewAdapter extends BaseEndLessRecyclerViewAdapter {
    private final List<File> mListBean;
    private final ImageFragment mImageFragment;

    public DeviceImageRecyclerViewAdapter(ImageFragment imageFragment, List<File> listBean) {
        this.mImageFragment = imageFragment;
        this.mListBean = listBean;
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
    protected boolean isEndLessScroll() {
        return false;
    }

    @Override
    protected void onBindTo(ViewDataBinding rowBinding, int position) {
        if (rowBinding instanceof RowGridBinding) {
            RowGridBinding mRowGridBinding = (RowGridBinding) rowBinding;
            mRowGridBinding.txtName.setText(mListBean.get(position).getName());
            ImageLoaderUtils.showImageUsingGLIDE(mListBean.get(position), mRowGridBinding.img, getPlaceHolder(1));
            mRowGridBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mImageFragment != null) mImageFragment.onItemClick(position);
                }
            });
        }
    }


}



