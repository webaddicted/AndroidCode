package com.example.deepaksharma.androidcode.view.language;


import android.content.Intent;
import android.view.View;

import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.deepaksharma.androidcode.R;
import com.example.deepaksharma.androidcode.databinding.ActivityCommonBinding;
import com.example.deepaksharma.androidcode.databinding.ActivityLanguageBinding;
import com.example.deepaksharma.androidcode.global.StorageInfoUtils;
import com.example.deepaksharma.androidcode.model.language.LanguageBean;
import com.example.deepaksharma.androidcode.utils.GlobalUtilities;
import com.example.deepaksharma.androidcode.view.adapter.DeviceImageRecyclerViewAdapter;
import com.example.deepaksharma.androidcode.view.adapter.LanguageAdapter;
import com.example.deepaksharma.androidcode.view.base.BaseActivity;
import com.example.deepaksharma.androidcode.view.fragment.ImageFragment;
import com.example.deepaksharma.androidcode.view.fragment.splash.SplashFragment;
import com.example.deepaksharma.androidcode.view.onBoarding.OnBoardActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class LanguageActivity extends BaseActivity {
    private ActivityLanguageBinding mBinding;
    private List<LanguageBean> languageBeanList;
    private LanguageAdapter mAdapter;

    @Override
    public int getLayout() {
        return R.layout.activity_language;
    }

    @Override
    public void initUI(ViewDataBinding binding) {
        mBinding = (ActivityLanguageBinding) binding;
        init();
    }

    private void init() {
        languageBeanList = new ArrayList<>();
        setLanguageBean();
        languageObserver(0);
        mBinding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateScreen(OnBoardActivity.TAG);
            }
        });
        setAdapter();
    }

    public void languageObserver(int position) {
        GlobalUtilities.changeLanguage(this, languageBeanList.get(position).getLanguageCode());
        mBinding.toolbar.txtTitle.setText(getString(R.string.select_language));
        mBinding.btnNext.setText(getString(R.string.submit));

    }

    private void setLanguageBean() {
        languageBeanList.add(new LanguageBean(0,
                "default (" + Locale.getDefault().getCountry().toLowerCase() + ")",
                Locale.getDefault().getLanguage(), ""));
        languageBeanList.add(new LanguageBean(1, "africa", "af", ""));
        languageBeanList.add(new LanguageBean(2, "Argentina", "ar", ""));
        languageBeanList.add(new LanguageBean(3, "Brunei", "bn", ""));
        languageBeanList.add(new LanguageBean(4, "africa", "cs", ""));
        languageBeanList.add(new LanguageBean(5, "Franch", "fr", ""));
        languageBeanList.add(new LanguageBean(6, "Hindi", "hi", ""));
        languageBeanList.add(new LanguageBean(7, "Japanese", "ja", ""));
        languageBeanList.add(new LanguageBean(8, "Chinese", "zh", ""));
    }

    private void setAdapter() {
        mAdapter = new LanguageAdapter(this, languageBeanList);
        mBinding.rvLanguage.setLayoutManager(new LinearLayoutManager(this));
        mBinding.rvLanguage.setAdapter(mAdapter);
    }

    /**
     * navigate on fragment
     *
     * @param tag represent navigation activity
     */
    private void navigateScreen(String tag) {
        if (mAdapter.selectedPos < 0) {
            GlobalUtilities.showToast(getString(R.string.please_select_language));
            return;
        } else
            GlobalUtilities.changeLanguage(this, languageBeanList.get(mAdapter.selectedPos).getLanguageCode());
        if (tag.equals(OnBoardActivity.TAG)){
            startActivity(new Intent(this, OnBoardActivity.class));
            finish();
        }
    }
}