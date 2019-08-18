package com.example.deepaksharma.androidcode.view.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.databinding.ViewDataBinding;

import com.example.deepaksharma.androidcode.R;
import com.example.deepaksharma.androidcode.databinding.FragmentDynamicLayoutBinding;
import com.example.deepaksharma.androidcode.databinding.RowDynamicLayoutBinding;
import com.example.deepaksharma.androidcode.utils.GlobalUtilities;
import com.example.deepaksharma.androidcode.view.base.BaseFragment;
import com.example.deepaksharma.androidcode.view.home.HomeActivity;

public class DynamicLayoutFragment extends BaseFragment {
    public static final String TAG = DynamicLayoutFragment.class.getSimpleName();
    private FragmentDynamicLayoutBinding mBinding;

    public static DynamicLayoutFragment getInstance(Bundle bundle) {
        DynamicLayoutFragment fragment = new DynamicLayoutFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_dynamic_layout;
    }

    @Override
    protected void onViewsInitialized(ViewDataBinding binding, View view) {
        mBinding = (FragmentDynamicLayoutBinding) binding;
        init();
    }

    private void init() {
        for (int i = 0; i < 8; i++) {
            RowDynamicLayoutBinding rowBinding =
                    (RowDynamicLayoutBinding) GlobalUtilities.getLayoutBinding(getActivity(), R.layout.row_dynamic_layout);
            rowBinding.txtDate.setText("19/07/2017" + " " + "to");
            rowBinding.txtAccountNo.setText("ICICI Bank 12355252255455");
            rowBinding.txtVehType.setText("sales");
            rowBinding.txtVehNo.setText("25");
            rowBinding.txtcredit.setText("25000");
            rowBinding.txtDebit.setText("40000");

            mBinding.linearAddLayout.addView(rowBinding.getRoot());
        }
        mBinding.totalCredit.setText(25000 * 8 + "");
        mBinding.totalDedit.setText(40000 * 8 + "");
    }
    @Override
    public void onResume() {
        super.onResume();
        ((HomeActivity) getActivity()).showBackBtn();
        ((HomeActivity) getActivity()).setToolbarTitle(getResources().getString(R.string.dynamic_layout_title));
    }
}
