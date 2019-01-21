package com.example.deepaksharma.androidcode.view.home;

import android.databinding.ViewDataBinding;
import android.support.v4.app.Fragment;
import android.view.View;

import com.example.deepaksharma.androidcode.R;
import com.example.deepaksharma.androidcode.databinding.ActivityHomeBinding;
import com.example.deepaksharma.androidcode.view.BaseActivity;
import com.example.deepaksharma.androidcode.view.fragment.TaskListFragment;
import com.example.deepaksharma.androidcode.view.fragment.WidgetFragment;

public class HomeActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = HomeActivity.class.getSimpleName();
    private ActivityHomeBinding mBinding;

    @Override
    public int getLayout() {
        return R.layout.activity_home;
    }

    @Override
    public void initUI(ViewDataBinding binding) {
        mBinding = (ActivityHomeBinding) binding;
        init();
        navigateScreen(TaskListFragment.TAG);
    }

    private void init() {
        mBinding.include.imgBack.setOnClickListener(this);
    }

    public void setToolbarTitle(String title) {
        mBinding.include.txtTitle.setText(title);
    }

    public void showBackBtn() {
        mBinding.include.imgBack.setVisibility(View.VISIBLE);
    }

    public void hideBackBtn() {
        mBinding.include.imgBack.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                onBackPressed();
                break;
        }
    }
    /**
     * navigate on fragment
     *
     * @param tag represent navigation activity
     */
    private void navigateScreen(String tag) {
        Fragment frm;
        if (tag.equals(TaskListFragment.TAG)) {
            frm = TaskListFragment.getInstance(getIntent().getExtras());
            navigateFragment(R.id.container, frm, true);
        }
    }

}

