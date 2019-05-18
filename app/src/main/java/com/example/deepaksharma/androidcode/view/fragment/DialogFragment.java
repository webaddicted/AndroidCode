package com.example.deepaksharma.androidcode.view.fragment;

import android.app.Dialog;
import android.databinding.ViewDataBinding;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.example.deepaksharma.androidcode.R;
import com.example.deepaksharma.androidcode.databinding.DialogCustomBinding;
import com.example.deepaksharma.androidcode.databinding.FragmentDialogBinding;
import com.example.deepaksharma.androidcode.global.DialogUtil;
import com.example.deepaksharma.androidcode.utils.GlobalUtilities;
import com.example.deepaksharma.androidcode.view.base.BaseFragment;
import com.example.deepaksharma.androidcode.view.dialog.LoginDialog;
import com.example.deepaksharma.androidcode.view.home.HomeActivity;

import java.util.ArrayList;
import java.util.List;

public class DialogFragment extends BaseFragment {
    public static final String TAG = DialogFragment.class.getSimpleName();
    private FragmentDialogBinding mBinding;

    public static DialogFragment getInstance(Bundle bundle) {
        DialogFragment fragment = new DialogFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_dialog;
    }

    @Override
    protected void onViewsInitialized(ViewDataBinding binding, View view) {
        mBinding = (FragmentDialogBinding) binding;
        clickListener();
    }

    private void clickListener() {
        mBinding.btnSingleClick.setOnClickListener(this);
        mBinding.btnTwoEventDialog.setOnClickListener(this);
        mBinding.btnCustomDialog.setOnClickListener(this);
        mBinding.btnDialogFragment.setOnClickListener(this);
        mBinding.btnSelectionList.setOnClickListener(this);
        mBinding.btnListDialog.setOnClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        ((HomeActivity) getActivity()).showBackBtn();
        ((HomeActivity) getActivity()).setToolbarTitle(getResources().getString(R.string.dialog_title));
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.btn_single_click:
                DialogUtil.showOkDialog(getActivity(), "Title", "msg", "ok", (dialog, which) -> {
                    GlobalUtilities.showToast("ok click" + which);
                    dialog.dismiss();
                });
                break;
            case R.id.btn_two_event_dialog:
                DialogUtil.showOkCancelDialog(getActivity(), "Title", "msg", (dialog, which) -> {
                    GlobalUtilities.showToast("ok click");
                    dialog.dismiss();
                }, (dialog, which) -> {
                    GlobalUtilities.showToast("cancel click");
                    dialog.dismiss();
                });
                break;
            case R.id.btn_custom_dialog:
                customDialog();
                break;
            case R.id.btn_dialog_fragment:
                LoginDialog loginDialog = new LoginDialog();
                loginDialog.show(getFragmentManager(), LoginDialog.TAG);
                break;
            case R.id.btn_selection_list:
                DialogUtil.getSingleChoiceDialog(getActivity(), getResources().getString(R.string.select_country), getCountryList(), (dialog, position) -> {
                    if (position > 0)
                        GlobalUtilities.showToast(getCountryList().get(position - 1).toString());
                    dialog.dismiss();
                }, (dialog, position) -> dialog.dismiss());
                break;
            case R.id.btn_list_dialog:
                DialogUtil.showListDialog(getActivity(), getResources().getString(R.string.select_country), getCountryList(), (dialog, which) -> {
                    GlobalUtilities.showToast(getCountryList().get(which).toString());
                    dialog.dismiss();
                });
                break;
        }
    }

    public List<String> getCountryList() {
        List<String> list = new ArrayList<>();
        list.add("India");
        list.add("Japan");
        list.add("Armenia");
        list.add("Australia");
        list.add("Bangladesh");
        return list;
    }

    private void customDialog() {
        Dialog dialog = new Dialog(getActivity(), R.style.AlertDialogStyle);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogSlideUpAnimation;
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        DialogCustomBinding dialogBinding = (DialogCustomBinding) GlobalUtilities.bindView(getActivity(), R.layout.dialog_custom);
        dialog.setContentView(dialogBinding.getRoot());
        dialogBinding.txtTitle.setText("this is demo");
        dialogBinding.txtMessage.setText(getResources().getString(R.string.marquee_txt));
        dialogBinding.btnOk.setOnClickListener(v -> {
            GlobalUtilities.showToast(getResources().getString(R.string.ok));
            dialog.dismiss();
        });
        dialogBinding.txtCancel.setOnClickListener(v -> {
            GlobalUtilities.showToast(getResources().getString(R.string.cancel));
            dialog.dismiss();
        });
        dialog.show();
    }

}
