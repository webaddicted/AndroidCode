package com.example.deepaksharma.androidcode.view.adapter;

import android.content.Context;
import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;

import com.example.deepaksharma.androidcode.R;
import com.example.deepaksharma.androidcode.utils.GlobalUtilities;

public class OptionItemMenu implements PopupMenu.OnMenuItemClickListener {

    private final Context mContext;

    public OptionItemMenu(Context context) {
        this.mContext = context;
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.delete:
                GlobalUtilities.showToast(mContext.getResources().getString(R.string.delete));
                return true;
            default:
        }
        return false;
    }
}