package com.example.deepaksharma.androidcode.view.fragment;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Dialog;
import android.databinding.ViewDataBinding;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.IdRes;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.Window;
import android.view.animation.OvershootInterpolator;
import android.widget.TextView;
import android.widget.Toast;

import com.example.deepaksharma.androidcode.R;
import com.example.deepaksharma.androidcode.databinding.CustomDialogBinding;
import com.example.deepaksharma.androidcode.databinding.FragmentBottomNaviBinding;
import com.example.deepaksharma.androidcode.databinding.FragmentFabBinding;
import com.example.deepaksharma.androidcode.global.DialogUtil;
import com.example.deepaksharma.androidcode.utils.GlobalUtilities;
import com.example.deepaksharma.androidcode.view.base.BaseFragment;
import com.example.deepaksharma.androidcode.view.dialog.LoginDialog;
import com.example.deepaksharma.androidcode.view.home.HomeActivity;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.BottomBarTab;
import com.roughike.bottombar.OnTabReselectListener;
import com.roughike.bottombar.OnTabSelectListener;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BottomNaviFragment extends BaseFragment {
    public static final String TAG = BottomNaviFragment.class.getSimpleName();
    private FragmentBottomNaviBinding mBinding;

    public static BottomNaviFragment getInstance(Bundle bundle) {
        BottomNaviFragment fragment = new BottomNaviFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_bottom_navi;
    }

    @Override
    protected void onViewsInitialized(ViewDataBinding binding, View view) {
        mBinding = (FragmentBottomNaviBinding) binding;

        mBinding.threeTabbottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                mBinding.txtThreeTabmessageView.setText(get(tabId, false));
            }
        });

        mBinding.threeTabbottomBar.setOnTabReselectListener(new OnTabReselectListener() {
            @Override
            public void onTabReSelected(@IdRes int tabId) {
                Toast.makeText(getActivity(), get(tabId, true), Toast.LENGTH_LONG).show();
            }
        });




        mBinding.fiveTabbottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                mBinding.txtFiveTabmessageView.setText(get(tabId, false));
            }
        });

        mBinding.fiveTabbottomBar.setOnTabReselectListener(new OnTabReselectListener() {
            @Override
            public void onTabReSelected(@IdRes int tabId) {
                Toast.makeText(getActivity(), get(tabId, true), Toast.LENGTH_LONG).show();
            }
        });

        mBinding.fiveTabColorBottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                mBinding.txtFiveColorTabmessageView.setText(get(tabId, false));
            }
        });

        mBinding.fiveTabColorBottomBar.setOnTabReselectListener(new OnTabReselectListener() {
            @Override
            public void onTabReSelected(@IdRes int tabId) {
                Toast.makeText(getActivity(), get(tabId, true), Toast.LENGTH_LONG).show();
            }
        });

        mBinding.customTabbottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                mBinding.txtCustomTabmessageView.setText(get(tabId, false));
            }
        });

        mBinding.customTabbottomBar.setOnTabReselectListener(new OnTabReselectListener() {
            @Override
            public void onTabReSelected(@IdRes int tabId) {
                Toast.makeText(getActivity(), get(tabId, true), Toast.LENGTH_LONG).show();
            }
        });
        mBinding.badgeBottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                mBinding.txtBadgeMessageView.setText(get(tabId, false));
            }
        });

        mBinding.badgeBottomBar.setOnTabReselectListener(new OnTabReselectListener() {
            @Override
            public void onTabReSelected(@IdRes int tabId) {
                Toast.makeText(getActivity(), get(tabId, true), Toast.LENGTH_LONG).show();
            }
        });

        BottomBarTab nearby = mBinding.badgeBottomBar.getTabWithId(R.id.tab_nearby);
        nearby.setBadgeCount(5);



    }

    public static String get(int menuItemId, boolean isReselection) {
        String message = "Content for ";

        switch (menuItemId) {
            case R.id.tab_recents:
                message += "recents";
                break;
            case R.id.tab_favorites:
                message += "favorites";
                break;
            case R.id.tab_nearby:
                message += "nearby";
                break;
            case R.id.tab_friends:
                message += "friends";
                break;
            case R.id.tab_food:
                message += "food";
                break;
        }

        if (isReselection) {
            message += " WAS RESELECTED! YAY!";
        }

        return message;
    }



    private void clickListener() {
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
                break;
        }
    }

}
