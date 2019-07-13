package com.example.deepaksharma.androidcode.view.fragment.splash;

import android.app.Dialog;
import android.content.Intent;
import android.databinding.ViewDataBinding;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.Window;

import com.example.deepaksharma.androidcode.R;
import com.example.deepaksharma.androidcode.databinding.ActivitySplashBinding;
import com.example.deepaksharma.androidcode.databinding.DialogCustomBinding;
import com.example.deepaksharma.androidcode.databinding.FragmentDialogBinding;
import com.example.deepaksharma.androidcode.global.DialogUtil;
import com.example.deepaksharma.androidcode.global.constant.AppConstant;
import com.example.deepaksharma.androidcode.utils.GlobalUtilities;
import com.example.deepaksharma.androidcode.view.base.BaseFragment;
import com.example.deepaksharma.androidcode.view.dialog.LoginDialog;
import com.example.deepaksharma.androidcode.view.fragment.AnimationFragment;
import com.example.deepaksharma.androidcode.view.fragment.ApiFragment;
import com.example.deepaksharma.androidcode.view.fragment.BarCodeFragment;
import com.example.deepaksharma.androidcode.view.fragment.BottomNaviFragment;
import com.example.deepaksharma.androidcode.view.fragment.CollpaseViewFragment;
import com.example.deepaksharma.androidcode.view.fragment.DialogFragment;
import com.example.deepaksharma.androidcode.view.fragment.DigitalSignatureFragment;
import com.example.deepaksharma.androidcode.view.fragment.DynamicLayoutFragment;
import com.example.deepaksharma.androidcode.view.fragment.ExpendableSpinnerListFragment;
import com.example.deepaksharma.androidcode.view.fragment.FabFragment;
import com.example.deepaksharma.androidcode.view.fragment.FingerPrintFragment;
import com.example.deepaksharma.androidcode.view.fragment.GetPhoneDetailFragment;
import com.example.deepaksharma.androidcode.view.fragment.GoogleMapFragment;
import com.example.deepaksharma.androidcode.view.fragment.GpsLocationFragment;
import com.example.deepaksharma.androidcode.view.fragment.ImageFragment;
import com.example.deepaksharma.androidcode.view.fragment.PaginationFragment;
import com.example.deepaksharma.androidcode.view.fragment.PdfFragment;
import com.example.deepaksharma.androidcode.view.fragment.ReceiverFragment;
import com.example.deepaksharma.androidcode.view.fragment.RecyclerViewFragment;
import com.example.deepaksharma.androidcode.view.fragment.SelectMultipleFileFragment;
import com.example.deepaksharma.androidcode.view.fragment.ServicesFragment;
import com.example.deepaksharma.androidcode.view.fragment.ShareFragment;
import com.example.deepaksharma.androidcode.view.fragment.SharePreferenceFragment;
import com.example.deepaksharma.androidcode.view.fragment.SpeechToTextFragment;
import com.example.deepaksharma.androidcode.view.fragment.SqliteDataBaseFragment;
import com.example.deepaksharma.androidcode.view.fragment.UiDesignFragment;
import com.example.deepaksharma.androidcode.view.fragment.ViewPagerTabFragment;
import com.example.deepaksharma.androidcode.view.fragment.WebViewFragment;
import com.example.deepaksharma.androidcode.view.fragment.WidgetFragment;
import com.example.deepaksharma.androidcode.view.home.HomeActivity;
import com.example.deepaksharma.androidcode.view.onBoarding.OnBoardActivity;
import com.example.deepaksharma.androidcode.view.splash.SplashActivity;

import java.util.ArrayList;
import java.util.List;

public class SplashFragment extends BaseFragment {
    public static final String TAG = SplashFragment.class.getSimpleName();
    private ActivitySplashBinding mBinding;

    public static SplashFragment getInstance(Bundle bundle) {
        SplashFragment fragment = new SplashFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void onViewsInitialized(ViewDataBinding binding, View view) {
        mBinding = (ActivitySplashBinding) binding;
        init();
    }

    private void init() {
        new Handler().postDelayed(() -> {
            startActivity(new Intent(getActivity(), OnBoardActivity.class));
            getActivity().finish();
        }, AppConstant.SPLASH_DELAY);
    }

}
