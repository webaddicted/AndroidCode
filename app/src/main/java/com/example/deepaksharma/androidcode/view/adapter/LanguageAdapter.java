package com.example.deepaksharma.androidcode.view.adapter;

import android.app.Activity;
import android.view.View;

import androidx.databinding.ViewDataBinding;

import com.example.deepaksharma.androidcode.R;
import com.example.deepaksharma.androidcode.databinding.RowLanguageBinding;
import com.example.deepaksharma.androidcode.global.ImageLoaderUtils;
import com.example.deepaksharma.androidcode.model.language.LanguageBean;
import com.example.deepaksharma.androidcode.view.base.BaseRecyclerViewAdapter;
import com.example.deepaksharma.androidcode.view.language.LanguageActivity;

import java.util.List;

public class LanguageAdapter extends BaseRecyclerViewAdapter {
    private final List<LanguageBean> mListBean;
    private final Activity mActivity;
    public int selectedPos = -1;

    public LanguageAdapter(Activity activity, List<LanguageBean> listBean) {
        this.mActivity = activity;
        this.mListBean = listBean;
    }

    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.row_language;
    }

    @Override
    protected int getListSize() {
        return (mListBean == null || mListBean.size() == 0) ? 0 : mListBean.size();
    }

    @Override
    protected void onBindTo(ViewDataBinding rowBinding, int position) {
        if (rowBinding instanceof RowLanguageBinding) {
            RowLanguageBinding mRowGridBinding = (RowLanguageBinding) rowBinding;
            mRowGridBinding.txtLanguageName.setText(mListBean.get(position).getLanguageName());
            ImageLoaderUtils.showImageUsingGLIDE(mListBean.get(position).getLanguageFlag(), mRowGridBinding.imgCountryFlag, getPlaceHolder(1));
            if (selectedPos == position) mRowGridBinding.rbLanguage.setChecked(true);
            else mRowGridBinding.rbLanguage.setChecked(false);
            mRowGridBinding.rbLanguage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectedPos = position;
                    ((LanguageActivity)mActivity).languageObserver(selectedPos);
                    notifyDataSetChanged();
                }
            });
        }
    }


}



