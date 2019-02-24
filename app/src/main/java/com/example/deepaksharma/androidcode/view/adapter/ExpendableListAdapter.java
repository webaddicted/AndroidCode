package com.example.deepaksharma.androidcode.view.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import com.example.deepaksharma.androidcode.R;
import com.example.deepaksharma.androidcode.databinding.RowTextListBinding;

public class ExpendableListAdapter extends BaseExpandableListAdapter {

    Context con;
    String paren[] = {"film", "game", "Appliction", "Wallpaper", "Theme"};
    String child[][] = {{"300", "DDLG", "Dshoom"}, {"pirates of carrabian", "nfs", "hgjb"},
            {"300", "DDLG", "Dshoom"}, {"pirates of carrabian", "nfs", "hgjb"}, {"jgjgj", "jhkhkh", "jggkgk", "jhkh"}};

    //int mage[]   = {R.drawable., R.drawable., R.drawable., R.drawable., R.drawable.};
//    public ExpendableListAdapter(Context c) {
//        super();
//    }

    @Override
    public int getGroupCount() {
        return paren.length;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return child[groupPosition].length;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupPosition;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return child[groupPosition][childPosition];
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        RowTextListBinding mBinding = null;
//        if (convertView == null) {
            mBinding = DataBindingUtil.
                    inflate(LayoutInflater.from(parent.getContext()),
                            R.layout.row_text_list,
                            parent, false);
//            convertView.setTag(mBinding);
//        } else
//            mBinding = (RowTextListBinding) convertView.getTag();
        mBinding.txtName.setText(paren[groupPosition]);
        mBinding.txtName.setPadding(2, 5, 5, 5);
        mBinding.card.setPadding(0, 2, 3, 5);
        return mBinding.getRoot();
    }

    @Override
    public View getChildView(int groupPosition, int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        RowTextListBinding mBinding = null;
//        if (convertView == null) {
            mBinding = DataBindingUtil.
                    inflate(LayoutInflater.from(parent.getContext()),
                            R.layout.row_text_list,
                            parent, false);
//            convertView.setTag(mBinding);
//        } else
//            mBinding = (RowTextListBinding) convertView.getTag();
        mBinding.txtName.setText(child[groupPosition][childPosition]);
        mBinding.txtName.setPadding(60, 5, 5, 5);
        mBinding.card.setPadding(60, 5, 2, 5);
        mBinding.card.setCardElevation(0);
        mBinding.card.setCardBackgroundColor(mBinding.txtName.getContext().getResources().getColor(R.color.transprant));
mBinding.getRoot().setBackgroundColor(mBinding.txtName.getContext().getResources().getColor(R.color.green));
        return mBinding.getRoot();
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
