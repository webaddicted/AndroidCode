package com.example.deepaksharma.androidcode.view.language;



import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;

import com.example.deepaksharma.androidcode.R;
import com.example.deepaksharma.androidcode.databinding.ActivityCommonBinding;
import com.example.deepaksharma.androidcode.view.base.BaseActivity;
import com.example.deepaksharma.androidcode.view.fragment.splash.SplashFragment;

public class LanguageActivity extends BaseActivity {
    private ActivityCommonBinding mBinding;

    @Override
    public int getLayout() {
        return R.layout.activity_common;
    }

    @Override
    public void initUI(ViewDataBinding binding) {
        mBinding = (ActivityCommonBinding) binding;
        navigateScreen(SplashFragment.TAG);
    }

    /**
     * navigate on fragment
     *
     * @param tag represent navigation activity
     */
    private void navigateScreen(String tag) {
        Fragment frm = null;
        if (tag.equals(SplashFragment.TAG))
            frm = SplashFragment.getInstance(getIntent().getExtras());
        navigateFragment(R.id.container, frm, false);
    }
}