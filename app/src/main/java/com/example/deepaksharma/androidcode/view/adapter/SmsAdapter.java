package com.example.deepaksharma.androidcode.view.adapter;


import androidx.databinding.ViewDataBinding;

import com.example.deepaksharma.androidcode.R;
import com.example.deepaksharma.androidcode.databinding.RowContactBinding;
import com.example.deepaksharma.androidcode.model.SmsBean;
import com.example.deepaksharma.androidcode.view.base.BaseEndLessRecyclerViewAdapter;
import com.example.deepaksharma.androidcode.view.fragment.ImageFragment;

import java.util.List;

public class SmsAdapter extends BaseEndLessRecyclerViewAdapter {
    private final List<SmsBean> mListBean;
    private final ImageFragment mImageFragment;

    public SmsAdapter(ImageFragment imageFragment, List<SmsBean> listBean) {
        this.mImageFragment = imageFragment;
        this.mListBean = listBean;
    }

    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.row_contact;
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
        if (rowBinding instanceof RowContactBinding) {
            RowContactBinding mRowGridBinding = (RowContactBinding) rowBinding;
            SmsBean smsBean = mListBean.get(position);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(smsBean.getFolderName() != null ? "sms folder name - " + smsBean.getFolderName() + "\n" : "");
            stringBuilder.append(smsBean.getAddress() != null ? "sms address - " + smsBean.getAddress() + "\n" : "");
            stringBuilder.append(smsBean.getMsg() != null ? "sms - " + smsBean.getMsg() + "\n" : "");
            stringBuilder.append(smsBean.getReadState() != null ? "sms read state - " + smsBean.getReadState() + "\n" : "");
            stringBuilder.append(smsBean.getTime() != null ? "sms time - " + smsBean.getTime() + "\n" : "");

            mRowGridBinding.txtName.setText(stringBuilder.toString());
//            ImageLoaderUtils.showImageUsingGLIDE(smsBean.getContactPhoto(), mRowGridBinding.imgUser, getPlaceHolder(1));

        }
    }


}



