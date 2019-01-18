package com.example.deepaksharma.androidcode.view;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.deepaksharma.androidcode.R;
import com.example.deepaksharma.androidcode.view.interfaces.LayoutListener;

import org.greenrobot.eventbus.EventBus;

public class BaseActivity extends AppCompatActivity implements LayoutListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);
        getSupportActionBar().hide();
        int layoutResId = getLayout();
        ViewDataBinding binding = null;
        if (!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this);
        if (layoutResId != 0) {
            try {
                binding = DataBindingUtil.setContentView(this, layoutResId);
                initUI(binding);
            } catch (Exception e) {
                e.printStackTrace();
//                Lg.e("Error <> ", Lg.getStackTraceString(e));
            }
        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out);
    }

    @Override
    public int getLayout() {
        return 0;
    }

    @Override
    public void initUI(ViewDataBinding binding) {

    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().unregister(this);
    }
}
