package com.example.deepaksharma.androidcode.view.dialog;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.view.View;

import com.example.deepaksharma.androidcode.R;
import com.example.deepaksharma.androidcode.databinding.CustomDialogBinding;
import com.example.deepaksharma.androidcode.global.DialogUtil;
import com.example.deepaksharma.androidcode.utils.GlobalUtilities;
import com.example.deepaksharma.androidcode.view.base.BaseDialogFragment;
import com.example.deepaksharma.androidcode.view.home.HomeActivity;

public class LoginDialog extends BaseDialogFragment {
    public static final String TAG = LoginDialog.class.getSimpleName();
    private CustomDialogBinding mBinding;

    public static LoginDialog getInstance(Bundle bundle) {
        LoginDialog fragment = new LoginDialog();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.custom_dialog;
    }

    @Override
    protected void onViewsInitialized(ViewDataBinding binding, View view) {
        mBinding = (CustomDialogBinding) binding;
        init();
        clickListener();
    }

    private void init() {
        mBinding.txtTitle.setText("this is demo");
        mBinding.txtMessage.setText(getResources().getString(R.string.marquee_txt));
    }

    private void clickListener() {
        mBinding.btnOk.setOnClickListener(this);
        mBinding.txtCancel.setOnClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        DialogUtil.modifyDialogBounds(getActivity(), getDialog());
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.btn_ok:
                GlobalUtilities.showToast("ok click");
                dismiss();
                break;
            case R.id.txt_cancel:
                GlobalUtilities.showToast("cancel click");
                dismiss();
                break;
        }
    }
}
