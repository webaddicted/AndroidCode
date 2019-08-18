package com.example.deepaksharma.androidcode.view.adapter;

import android.graphics.Color;
import android.location.Location;

import androidx.databinding.ViewDataBinding;

import com.example.deepaksharma.androidcode.R;
import com.example.deepaksharma.androidcode.databinding.RowContactBinding;
import com.example.deepaksharma.androidcode.view.base.BaseRecyclerViewAdapter;

import java.util.List;
import java.util.Random;

public class LocationAdapter extends BaseRecyclerViewAdapter {
    private List<Location> mLocationBean;

    public LocationAdapter(List<Location> locationBean) {
        this.mLocationBean = locationBean;
    }

    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.row_contact;
    }

    @Override
    protected int getListSize() {
        return (mLocationBean== null || mLocationBean.size() == 0) ? 0 : mLocationBean.size();
    }

    @Override
    protected void onBindTo(ViewDataBinding rowBinding, int position) {
        if (rowBinding instanceof RowContactBinding) {
            RowContactBinding mRowGridBinding = (RowContactBinding) rowBinding;
            Location location = mLocationBean.get(position);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Latitude - " + location.getLatitude()+"\n");
            stringBuilder.append("Longitude - " + location.getLongitude()+"\n");
            stringBuilder.append("print time- " + System.currentTimeMillis());

            mRowGridBinding.imgUser.setImageDrawable(mContext.getResources().getDrawable(R.drawable.logo));
            mRowGridBinding.txtName.setText(stringBuilder.toString());
            Random rnd = new Random();
            int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
            mRowGridBinding.card.setCardBackgroundColor(color);
        }
    }

    public void notifyUi(List<Location> mLocationBean) {
        this.mLocationBean = mLocationBean;
        notifyDataSetChanged();
    }
}



