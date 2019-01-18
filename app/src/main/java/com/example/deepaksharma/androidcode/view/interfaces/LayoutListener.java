package com.example.deepaksharma.androidcode.view.interfaces;

import android.databinding.ViewDataBinding;

public interface LayoutListener {

    public int getLayout();

    public void initUI(ViewDataBinding binding);

    interface OKClickEventInterface {
        public void OnClickOKButton();
    }
}
