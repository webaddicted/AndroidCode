package com.example.deepaksharma.androidcode.view.fragment;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.example.deepaksharma.androidcode.R;
import com.example.deepaksharma.androidcode.databinding.CustomTabViewBinding;
import com.example.deepaksharma.androidcode.databinding.FragmentViewPagerTabBinding;
import com.example.deepaksharma.androidcode.global.ViewPagerAdapter;
import com.example.deepaksharma.androidcode.utils.GlobalUtilities;
import com.example.deepaksharma.androidcode.view.base.BaseFragment;
import com.example.deepaksharma.androidcode.view.home.HomeActivity;

public class ViewPagerTabFragment extends BaseFragment {
    public static final String TAG = ViewPagerTabFragment.class.getSimpleName();
    private FragmentViewPagerTabBinding mBinding;
    private ImageFragment frmImg;
    private AnimationFragment frmAnim;
    private RecyclerViewFragment frmRecycler;
    String[] tabTitle = {"CALLS", "CHAT", "CONTACTS"};
    int[] unreadCount = {0, 5, 0};

    public static ViewPagerTabFragment getInstance(Bundle bundle) {
        ViewPagerTabFragment fragment = new ViewPagerTabFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_view_pager_tab;
    }

    @Override
    protected void onViewsInitialized(ViewDataBinding binding, View view) {
        mBinding = (FragmentViewPagerTabBinding) binding;
        init();
    }

    private void init() {
        mBinding.viewPager.setOffscreenPageLimit(3);
        mBinding.tabCustomIcon.setupWithViewPager(mBinding.viewPager);
        setupTabIcons();
        mBinding.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mBinding.viewPager.setCurrentItem(position, false);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        setupViewPager(mBinding.viewPager);

        // other tab
        addTabs(mBinding.tabWithoutIcon, true);
//        addTabs(mBinding.tabWithIcon, true);

    }

    @Override
    public void onResume() {
        super.onResume();
        ((HomeActivity) getActivity()).showBackBtn();
        ((HomeActivity) getActivity()).setToolbarTitle(getResources().getString(R.string.view_pager_tab_title));
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.btn_share_text:
                break;
        }
    }

    private void setupTabIcons() {
        for (int i = 0; i < tabTitle.length; i++) {
            TabLayout.Tab tabitem = mBinding.tabCustomIcon.newTab();
            tabitem.setCustomView(prepareTabView(i));
            mBinding.tabCustomIcon.addTab(tabitem);
//            mBinding.tabCustomIcon.getTabAt(i).setCustomView(prepareTabView(i).getRootView());
        }
    }

    private View prepareTabView(int pos) {
        CustomTabViewBinding customBinding = (CustomTabViewBinding) GlobalUtilities.getLayoutBinding(getActivity(), R.layout.custom_tab_view);
        customBinding.tvTitle.setText(tabTitle[pos]);
        if (unreadCount[pos] > 0) {
            customBinding.tvCount.setVisibility(View.VISIBLE);
            customBinding.tvCount.setText("" + unreadCount[pos]);
        } else
            customBinding.tvCount.setVisibility(View.GONE);
        return customBinding.getRoot();
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getFragmentManager());
        frmImg = new ImageFragment();
        frmAnim = new AnimationFragment();
        frmRecycler = new RecyclerViewFragment();
        adapter.addFragment(frmImg, "Image");
        adapter.addFragment(frmAnim, "Animation");
        adapter.addFragment(frmRecycler, "Recycler");
        viewPager.setAdapter(adapter);
    }

    private void addTabs(TabLayout tabLayout, boolean hasIcon) {
        for (int i = 0; i < tabTitle.length; i++) {
            TabLayout.Tab tabitem = tabLayout.newTab();
            if (hasIcon)
                tabitem.setCustomView(prepareTabView(i));
            tabLayout.addTab(tabitem);
//            mBinding.tabCustomIcon.getTabAt(i).setCustomView(prepareTabView(i));
        }
    }
}