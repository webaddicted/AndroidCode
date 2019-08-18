package com.example.deepaksharma.androidcode.view.fragment;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

import androidx.databinding.ViewDataBinding;

import com.example.deepaksharma.androidcode.R;
import com.example.deepaksharma.androidcode.databinding.FragmentWebviewBinding;
import com.example.deepaksharma.androidcode.utils.WebViewPageClient;
import com.example.deepaksharma.androidcode.view.base.BaseFragment;
import com.example.deepaksharma.androidcode.view.home.HomeActivity;

public class WebViewFragment extends BaseFragment {
    public static final String TAG = WebViewFragment.class.getSimpleName();
    private FragmentWebviewBinding mBinding;
    private String mLink;

    public static WebViewFragment getInstance(Bundle bundle) {
        WebViewFragment fragment = new WebViewFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_webview;
    }

    @Override
    protected void onViewsInitialized(ViewDataBinding binding, View view) {
        mBinding = (FragmentWebviewBinding) binding;
        init();
        clickListener();
    }

    private void init() {
        mLink = "https://www.facebook.com";
        showWebView(mBinding.webView);
    }


    private void clickListener() {
    }

    private void showWebView(WebView webView) {
        try {
            mBinding.loader.parentLoader.setVisibility(View.VISIBLE);
            webView.getSettings().setJavaScriptEnabled(true);
            //web.getSettings().getAllowUniversalAccessFromFileURLs();
            webView.getSettings().setLoadWithOverviewMode(true);
            webView.getSettings().setUseWideViewPort(true);
            webView.setWebViewClient(new WebViewPageClient(mBinding.loader.parentLoader));
            webView.loadUrl(mLink);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        ((HomeActivity) getActivity()).showBackBtn();
        ((HomeActivity) getActivity()).setToolbarTitle(getResources().getString(R.string.web_view_title));
    }
    // To handle &quot;Back&quot; key press event for WebView to go back to previous screen.
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
//            webView.goBack();
//            return true;
//        }
//        return super.onKeyDown(keyCode, event);
//    }

}
