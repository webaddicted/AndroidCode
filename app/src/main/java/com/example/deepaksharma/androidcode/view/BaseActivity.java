package com.example.deepaksharma.androidcode.view;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.deepaksharma.androidcode.R;
import com.example.deepaksharma.androidcode.model.eventBus.EventBusListener;
import com.example.deepaksharma.androidcode.view.interfaces.LayoutListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class BaseActivity extends AppCompatActivity implements LayoutListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int layoutResId = getLayout();
        overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);
        getSupportActionBar().hide();
        ViewDataBinding binding = null;
        if (!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this);
        if (layoutResId != 0) {
            try {
                binding = DataBindingUtil.setContentView(this, layoutResId);
                initUI(binding);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public int getLayout() {
        return 0;
    }

    @Override
    public void initUI(ViewDataBinding binding) {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void EventBusListener(EventBusListener eventBusListener) {
    }

    protected void navigateFragment(int layoutContainer, Fragment fragment, boolean isEnableBackStack) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(layoutContainer, fragment);
        if (isEnableBackStack)
            fragmentTransaction.addToBackStack(fragment.getClass().getSimpleName());
        fragmentTransaction.commitAllowingStateLoss();
    }
}
