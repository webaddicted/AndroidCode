package com.example.deepaksharma.androidcode.global;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;

import com.example.deepaksharma.androidcode.R;


/**
 * Created by Deepak Sharma on 31/10/18.
 */

/**
 * open term & condition url in webview and check ssl error in WebViewClientSSl
 */
public class WebViewClientSSl extends WebViewClient {

    private final FrameLayout mProgressBar;
    private Context mContext;
    private CertificateListener mCertificateListener;

    public WebViewClientSSl(Context context, FrameLayout progressBar, CertificateListener certificateListener) {
        this.mContext = context;
        this.mProgressBar = progressBar;
        this.mCertificateListener = certificateListener;
        if (progressBar != null && !progressBar.isShown())
            progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        // TODO Auto-generated method stub
        super.onPageStarted(view, url, favicon);
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        if (mProgressBar != null)
            mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        // TODO Auto-generated method stub
        view.loadUrl(url);
        return true;
    }

    @Override
    public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
        String message = mContext.getResources().getString(R.string.ssl_certificate_error);
        switch (error.getPrimaryError()) {
            case SslError.SSL_UNTRUSTED:
                message = mContext.getResources().getString(R.string.ssl_certificate_untrusted);
                break;
            case SslError.SSL_EXPIRED:
                message = mContext.getResources().getString(R.string.ssl_certificate_expired);
                break;
            case SslError.SSL_IDMISMATCH:
                message = mContext.getResources().getString(R.string.ssl_certificate_mismatch);
                break;
            case SslError.SSL_NOTYETVALID:
                message = mContext.getResources().getString(R.string.ssl_certificate_notvalid);
                break;
        }
        if (mCertificateListener != null)
            mCertificateListener.certificateFailure(message, handler);
    }

    /**
     * handle ssl error by CertificateListener
     */
    public interface CertificateListener {
        void certificateFailure(String message, SslErrorHandler handler);
    }
}