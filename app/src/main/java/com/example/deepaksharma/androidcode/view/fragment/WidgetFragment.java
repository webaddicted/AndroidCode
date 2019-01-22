package com.example.deepaksharma.androidcode.view.fragment;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.RadioButton;

import com.example.deepaksharma.androidcode.R;
import com.example.deepaksharma.androidcode.databinding.FragmentWidgetBinding;
import com.example.deepaksharma.androidcode.global.ValidationHelper;
import com.example.deepaksharma.androidcode.view.BaseFragment;
import com.example.deepaksharma.androidcode.view.home.HomeActivity;

public class WidgetFragment extends BaseFragment implements View.OnClickListener {
    public static final String TAG = WidgetFragment.class.getSimpleName();
    private FragmentWidgetBinding mBinding;

    public static WidgetFragment getInstance(Bundle bundle) {
        WidgetFragment fragment = new WidgetFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_widget;
    }

    @Override
    protected void onViewsInitialized(ViewDataBinding binding, View view) {
        mBinding = (FragmentWidgetBinding) binding;
        init();
        clickListener();
    }

    private void init() {
    }

    private void clickListener() {
        mBinding.btnLogin.setOnClickListener(this);
        mBinding.edtEmail.addTextChangedListener(new EditTextListener(mBinding.edtEmail));

        mBinding.rg.setOnCheckedChangeListener((group, checkedId) -> {
            int selectedId = mBinding.rg.getCheckedRadioButtonId();
            RadioButton rb = (RadioButton) getView().findViewById(selectedId);
            mBinding.cbNone.setTextColor(getResources().getColor(R.color.app_color));
            mBinding.cbUnselected.setTextColor(getResources().getColor(R.color.app_color));
            mBinding.cbSelected.setTextColor(getResources().getColor(R.color.app_color));
            rb.setTextColor(getResources().getColor(R.color.green));
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        ((HomeActivity) getActivity()).showBackBtn();
        ((HomeActivity) getActivity()).setToolbarTitle(getResources().getString(R.string.widget_view_title));
    }
    private class EditTextListener implements TextWatcher {
        TextInputEditText textInput;

        public EditTextListener(TextInputEditText edtText) {
            this.textInput = edtText;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            switch (textInput.getId()) {
                case R.id.edt_email:
                    ValidationHelper.validateEmail(mBinding.edtEmail, mBinding.wrpperEmail);
                    break;
                case R.id.edt_pwd:
                    ValidationHelper.validatePwd(mBinding.edtPwd, mBinding.wrapperPwd);
                    break;
            }
            validate();
        }
    }

    private void validate() {

    }
}
