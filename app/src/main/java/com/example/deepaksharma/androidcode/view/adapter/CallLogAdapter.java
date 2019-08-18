package com.example.deepaksharma.androidcode.view.adapter;


import androidx.databinding.ViewDataBinding;

import com.example.deepaksharma.androidcode.R;
import com.example.deepaksharma.androidcode.databinding.RowContactBinding;
import com.example.deepaksharma.androidcode.model.common.CallLogBean;
import com.example.deepaksharma.androidcode.view.base.BaseEndLessRecyclerViewAdapter;
import com.example.deepaksharma.androidcode.view.fragment.ImageFragment;

import java.util.List;

public class CallLogAdapter extends BaseEndLessRecyclerViewAdapter {
    private final List<CallLogBean> mListBean;
    private final ImageFragment mImageFragment;

    public CallLogAdapter(ImageFragment imageFragment, List<CallLogBean> listBean) {
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
            CallLogBean callLogBean = mListBean.get(position);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(callLogBean.getmId() != null ? "call id  - " + callLogBean.getmId() + "\n" : "");
            stringBuilder.append(callLogBean.getPhNumber() != null ? "call phone number  - " + callLogBean.getPhNumber() + "\n" : "");
            stringBuilder.append(callLogBean.getmName() != null ? "caller name - " + callLogBean.getmName() + "\n" : "");
            stringBuilder.append(callLogBean.getmIsRead() != null ? "call read state  - " + callLogBean.getmIsRead() + "\n" : "");
            stringBuilder.append(callLogBean.getmPhotoUri() != null ? "call photo - " + callLogBean.getmPhotoUri() + "\n" : "");
            stringBuilder.append(callLogBean.getCallDate() != null ? "call date - " + callLogBean.getCallDate() + "\n" : "");
            stringBuilder.append(callLogBean.getCallDayTime() != null ? "call day time  - " + callLogBean.getCallDayTime() + "\n" : "");
            stringBuilder.append(callLogBean.getCallDuration() != null ? "call duration - " + callLogBean.getCallDuration() + "\n" : "");
            stringBuilder.append(callLogBean.getCallType() != null ? "call type - " + callLogBean.getCallType() + "\n" : "");
            stringBuilder.append(callLogBean.getmCount() != null ? "call count- " + callLogBean.getmCount() + "\n" : "");
            stringBuilder.append(callLogBean.getDir() != null ? "call dir - " + callLogBean.getDir() + "\n" : "");

            mRowGridBinding.txtName.setText(stringBuilder.toString());
//            ImageLoaderUtils.showImageUsingGLIDE(smsBean.getContactPhoto(), mRowGridBinding.imgUser, getPlaceHolder(1));

        }
    }


}



