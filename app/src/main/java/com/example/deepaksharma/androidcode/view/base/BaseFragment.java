package com.example.deepaksharma.androidcode.view.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.deepaksharma.androidcode.R;
import com.example.deepaksharma.androidcode.model.eventBus.EventBusListener;
import com.example.deepaksharma.androidcode.utils.GlobalUtilities;
import com.theartofdev.edmodo.cropper.CropImage;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Created by Deepak Sharma on 15/1/19.
 */
public abstract class BaseFragment extends Fragment implements View.OnClickListener {

    private ViewDataBinding mBinding;

    protected abstract int getLayoutId();

    protected abstract void onViewsInitialized(ViewDataBinding binding, View view);

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
        if (!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        onViewsInitialized(mBinding, view);
        super.onViewCreated(view, savedInstanceState);
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
        if (getActivity() != null) {
            ((BaseActivity) getActivity()).navigateFragment(layoutContainer, fragment, isEnableBackStack);
        }
    }
    protected void navigateAddFragment(int layoutContainer, Fragment fragment, boolean isEnableBackStack) {
        if (getActivity() != null) {
            ((BaseActivity) getActivity()).navigateAddFragment(layoutContainer, fragment, isEnableBackStack);
        }
    }
    protected void navigateChildFragment(int layoutContainer, Fragment fragment, boolean isEnableBackStack) {
        FragmentManager fragmentManager = getChildFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(layoutContainer, fragment);
        if (isEnableBackStack)
            fragmentTransaction.addToBackStack(fragment.getClass().getSimpleName());
        fragmentTransaction.commitAllowingStateLoss();
    }

    @Override
    public void onClick(View v) {
        GlobalUtilities.btnClickAnimation(v);
    }

    public void getLocation() {
        ((BaseLocation) getActivity()).getLocation();
    }

    public void getLocation(@NonNull long timeInterval, @NonNull long fastInterval, @NonNull long displacement) {
        ((BaseLocation) getActivity()).getLocation(timeInterval, fastInterval, displacement);
    }

    public void getLocationWithAddress(@NonNull long timeInterval, @NonNull long fastInterval, @NonNull long displacement) {
        ((BaseLocation) getActivity()).getLocation(timeInterval, fastInterval, displacement);
        ((BaseLocation) getActivity()).isAddressEnabled(true);
    }

    public void stopUpdateLocation() {
        ((BaseLocation) getActivity()).stopLocationUpdates();
    }

    public void checkStoragePermission() {
        ((BaseActivity) getActivity()).checkStoragePermission();
    }
    public void checkLocationPermission() {
        ((BaseActivity) getActivity()).checkLocationPermission();
    }
    public String getImageLoader(int imageLoaderPos) {
        String[] imageLoader = getResources().getStringArray(R.array.image_loader);
        return imageLoader[imageLoaderPos];
    }

}
