package com.example.deepaksharma.androidcode.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.databinding.DataBindingUtil;

import com.example.deepaksharma.androidcode.R;
import com.example.deepaksharma.androidcode.databinding.RowTextListBinding;

import java.util.List;

public class SpinnerListAdapter extends BaseAdapter {
    private final List<String> mListBean;

    public SpinnerListAdapter(List<String> listBean) {
        this.mListBean = listBean;
    }


    public int getCount() {
        return (mListBean == null || mListBean.size() == 0) ? 0 : mListBean.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        RowTextListBinding mBinding = null;
//        if (convertView == null) {
            mBinding = DataBindingUtil.
                    inflate(LayoutInflater.from(parent.getContext()),
                            R.layout.row_text_list,
                            parent, false);
//            convertView.setTag(mBinding);
//        } else
//            mBinding = (RowTextListBinding) convertView.getTag();
        mBinding.txtName.setText(mListBean.get(position));
        return mBinding.getRoot();
    }
}
