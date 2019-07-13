package com.example.deepaksharma.androidcode.view.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class OnBordingViewPagerAdapter extends PagerAdapter {
    private final int[] layouts;
    private LayoutInflater layoutInflater;

    public OnBordingViewPagerAdapter(Activity activity, int[] layouts) {
        layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.layouts = layouts;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = layoutInflater.inflate(layouts[position], container, false);
        container.addView(view);
        return view;
    }

    @Override
    public int getCount() {
        return (layouts != null) ? layouts.length : 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object obj) {
        return (view == obj);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view = (View) object;
        container.removeView(view);
    }
}