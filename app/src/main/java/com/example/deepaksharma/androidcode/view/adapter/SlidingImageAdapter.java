package com.example.deepaksharma.androidcode.view.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.deepaksharma.androidcode.R;
import com.example.deepaksharma.androidcode.databinding.RowImagesSlideBinding;
import com.example.deepaksharma.androidcode.global.ImageLoaderUtils;
import com.example.deepaksharma.androidcode.global.listener.DoubleClickListener;

import java.io.File;
import java.util.List;

/**
 * Created by Anish Sharma on 30-04-2018.
 */

public class SlidingImageAdapter extends PagerAdapter {
    private List<File> mImageFolderList;

    public SlidingImageAdapter(List<File> imageFolderList) {
        mImageFolderList = imageFolderList;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return mImageFolderList.size();
    }

    @Override
    public Object instantiateItem(ViewGroup parent, int position) {
        RowImagesSlideBinding rowBinding = DataBindingUtil.
                inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.row_images_slide,
                        parent, false);
        String[] imageLoader = parent.getContext().getResources().getStringArray(R.array.image_loader);
        ImageLoaderUtils.showImageUsingGLIDE(mImageFolderList.get(position), rowBinding.photoView,imageLoader[1]);
        parent.addView(rowBinding.getRoot(), 0);
        rowBinding.photoView.setOnClickListener(new DoubleClickListener() {
            @Override
            public void onSingleClick(View v) {

            }

            @Override
            public void onDoubleClick(View v) {

            }
        });
        return rowBinding.getRoot();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
    }

    @Override
    public Parcelable saveState() {
        return null;
    }

}