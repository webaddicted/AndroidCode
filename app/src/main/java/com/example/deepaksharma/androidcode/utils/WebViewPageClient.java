package com.example.deepaksharma.androidcode.utils;

import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

public class WebViewPageClient extends WebViewClient {
    private final LinearLayout mParentLoader;

    public WebViewPageClient(LinearLayout parentLoader) {
        this.mParentLoader = parentLoader;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView webView, String url) {
        webView.loadUrl(url);
        return true;
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        mParentLoader.setVisibility(View.GONE);
    }
}
