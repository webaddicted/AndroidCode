package com.example.deepaksharma.androidcode.utils;

import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebViewPageClient extends WebViewClient {
    @Override
    public boolean shouldOverrideUrlLoading(WebView webView, String url) {
        webView.loadUrl(url);
        return true;
    }
}
