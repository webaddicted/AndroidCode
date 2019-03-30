package com.example.deepaksharma.androidcode.view.adapter;

import android.databinding.ViewDataBinding;
import android.view.View;

import com.example.deepaksharma.androidcode.R;
import com.example.deepaksharma.androidcode.databinding.RowContactBinding;
import com.example.deepaksharma.androidcode.databinding.RowGridBinding;
import com.example.deepaksharma.androidcode.global.ImageLoaderUtils;
import com.example.deepaksharma.androidcode.model.common.ContactsBean;
import com.example.deepaksharma.androidcode.view.base.BaseRecyclerViewAdapter;
import com.example.deepaksharma.androidcode.view.fragment.ImageFragment;

import java.io.File;
import java.util.List;

public class ContactAdapter extends BaseRecyclerViewAdapter {
    private final List<ContactsBean> mListBean;
    private final ImageFragment mImageFragment;

    public ContactAdapter(ImageFragment imageFragment, List<ContactsBean> listBean) {
        this.mImageFragment = imageFragment;
        this.mListBean = listBean;
    }

    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.row_contact;
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
        if (rowBinding instanceof RowContactBinding) {
            RowContactBinding mRowGridBinding = (RowContactBinding) rowBinding;
            ContactsBean contactsBean = mListBean.get(position);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(contactsBean.getContactName() != null ? "Name - " + contactsBean.getContactName() + "\n" : "");
            stringBuilder.append(contactsBean.getContactNumber() != null ? "Contact No - " + contactsBean.getContactNumber() + "\n" : "");
            stringBuilder.append(contactsBean.getContactEmail() != null ? "Email id - " + contactsBean.getContactEmail() + "\n" : "");
            stringBuilder.append(contactsBean.getContactPhoto() != null ? "photo - " + contactsBean.getContactPhoto() + "\n" : "");
            stringBuilder.append(contactsBean.getContactOtherDetails() != null ? "contact details - " + contactsBean.getContactOtherDetails() + "\n" : "");

            mRowGridBinding.txtName.setText(stringBuilder.toString());
            ImageLoaderUtils.showImageUsingGLIDE(contactsBean.getContactPhoto(), mRowGridBinding.imgUser, getPlaceHolder(1));
        }
    }


}



