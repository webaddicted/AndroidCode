package com.example.deepaksharma.androidcode.view.fragment;

import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.deepaksharma.androidcode.R;
import com.example.deepaksharma.androidcode.databinding.FragmentBarcodeBinding;
import com.example.deepaksharma.androidcode.utils.GlobalUtilities;
import com.example.deepaksharma.androidcode.view.base.BaseFragment;
import com.example.deepaksharma.androidcode.view.home.HomeActivity;
import com.example.deepaksharma.androidcode.viewModel.home.HomeViewModel;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;

public class BarCodeFragment extends BaseFragment {
    public static final String TAG = BarCodeFragment.class.getSimpleName();
    private FragmentBarcodeBinding mBinding;
    private IntentIntegrator mQrScan;
    private HomeViewModel mHomeViewModel;

    public static BarCodeFragment getInstance(Bundle bundle) {
        BarCodeFragment fragment = new BarCodeFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_barcode;
    }

    @Override
    protected void onViewsInitialized(ViewDataBinding binding, View view) {
        mBinding = (FragmentBarcodeBinding) binding;
        mHomeViewModel = ViewModelProviders.of(getActivity()).get(HomeViewModel.class);
        init();
        clickListener();
    }

    private void init() {
        mQrScan = new IntentIntegrator(getActivity());
    }

    private void clickListener() {
        mBinding.btnBarCode.setOnClickListener(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.btn_bar_code:
                mQrScan.initiateScan();
                getBarCodeInfo();
                break;
        }
    }

    private void getBarCodeInfo() {
        mHomeViewModel.mBarCodeResult.observe(this, new Observer<IntentResult>() {
            @Override
            public void onChanged(@Nullable IntentResult intentResult) {
                if (intentResult != null) {
                    mBinding.txtResult.setText(intentResult.getContents());
                    mBinding.txtResult.setText(intentResult.getContents());
                    if (intentResult.getContents() == null) {
                        GlobalUtilities.showToast("Result Not Found");
                    } else {
                        try {
                            JSONObject obj = new JSONObject(intentResult.getContents());
                        } catch (JSONException e) {
                            e.printStackTrace();
                            //if control comes here
                            //that means the encoded format not matches
                            //in this case you can display whatever data is available on the qrcode
                            //to a toast
                            GlobalUtilities.showToast(intentResult.getContents());
                            if(intentResult.getContents().contains("http") ||intentResult.getContents().contains("com")){
//                                GoWebSite((intentResult.getContents()));
                            }
                        }
                    }
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        ((HomeActivity) getActivity()).showBackBtn();
        ((HomeActivity) getActivity()).setToolbarTitle(getResources().getString(R.string.animation_title));
    }

}
