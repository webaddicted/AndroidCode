package com.example.deepaksharma.androidcode.view.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.databinding.ViewDataBinding;

import com.example.deepaksharma.androidcode.R;
import com.example.deepaksharma.androidcode.databinding.FragmentSharePreferenceBinding;
import com.example.deepaksharma.androidcode.global.constant.PreferenceConstant;
import com.example.deepaksharma.androidcode.global.sharedPref.PreferenceMgr;
import com.example.deepaksharma.androidcode.model.preference.PreferenceBean;
import com.example.deepaksharma.androidcode.utils.GlobalUtilities;
import com.example.deepaksharma.androidcode.view.base.BaseFragment;
import com.example.deepaksharma.androidcode.view.home.HomeActivity;

public class SharePreferenceFragment extends BaseFragment {
  public static final String TAG = SharePreferenceFragment.class.getSimpleName();
  private FragmentSharePreferenceBinding mBinding;

  public static SharePreferenceFragment getInstance(Bundle bundle) {
    SharePreferenceFragment fragment = new SharePreferenceFragment();
    fragment.setArguments(bundle);
    return fragment;
  }

  @Override
  protected int getLayoutId() {
    return R.layout.fragment_share_preference;
  }

  @Override
  protected void onViewsInitialized(ViewDataBinding binding, View view) {
    mBinding = (FragmentSharePreferenceBinding) binding;
    init();
  }

  private void init() {
    mBinding.btnSetValue.setOnClickListener(this);
    mBinding.btnGetValueFromPreference.setOnClickListener(this);
    mBinding.btnRemoveKey.setOnClickListener(this);
    mBinding.btnClearPreference.setOnClickListener(this);
  }

  @Override
  public void onClick(View v) {
    super.onClick(v);
    switch (v.getId()) {
      case R.id.btn_set_value:
        setUserInfo();
        break;
      case R.id.btn_get_value_from_preference:
        getUserInfo();
        break;
      case R.id.btn_remove_key:
        removeKey();
        break;
      case R.id.btn_clear_preference:
        clearPreference();
        break;

    }
  }

  public void setUserInfo() {
    PreferenceBean preferenceBean = new PreferenceBean();
    preferenceBean.setName("deepak sharma");
    preferenceBean.setAge(28);
    preferenceBean.setGender("male");
    preferenceBean.setMarried(false);
    preferenceBean.setWeight(62);
    PreferenceMgr.setUserInfo(preferenceBean);
    mBinding.txtSavePreference.setText(getResources().getString(R.string.successfully_save));
  }

  public void getUserInfo() {
    PreferenceBean preferenceBean = PreferenceMgr.getUserInfo();
    if (preferenceBean != null) {
      mBinding.txtGetPreference.setText("Name - " + preferenceBean.getName() +
              "\nAge - " + preferenceBean.getAge() +
              "\nGender - " + preferenceBean.getGender() +
              "\nmarried - " + preferenceBean.isMarried() +
              "\nweight - " + preferenceBean.getWeight());
    } else mBinding.txtGetPreference.setText(getResources().getString(R.string.no_data_found));
  }

  public void removeKey() {
    PreferenceMgr.removeKey(PreferenceConstant.PREF_USER_NAME);
    mBinding.txtRemoveKey.setText(getResources().getString(R.string.successfully_remove_key));
  }

  public void clearPreference() {
    PreferenceMgr.clearPreference();
    mBinding.txtClearPreference.setText(getResources().getString(R.string.successfully_clear_preference));
  }

  @Override
  public void onResume() {
    super.onResume();
    ((HomeActivity) getActivity()).showBackBtn();
    ((HomeActivity) getActivity()).setToolbarTitle(getResources().getString(R.string.shared_preference_title));
  }
}
