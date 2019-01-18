package com.example.deepaksharma.androidcode.global;

import android.app.Application;
import android.content.IntentFilter;
import android.net.ConnectivityManager;

import com.example.deepaksharma.androidcode.R;
import com.example.deepaksharma.androidcode.services.NetworkChangeReceiver;
import com.example.deepaksharma.androidcode.utils.GlobalUtilities;
import com.facebook.stetho.Stetho;
import com.nostra13.universalimageloader.core.ImageLoader;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class AppApplication extends Application {
    private static final String TAG = AppApplication.class.getSimpleName();
    private NetworkChangeReceiver mNetworkReceiver = new NetworkChangeReceiver();
    private static AppApplication mInstance;
    private boolean mIsNetworkConnected;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        Stetho.initializeWithDefaults(this);
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder().setDefaultFontPath("fonts/Ciscopic-Regular.ttf").setFontAttrId(R.attr.fontPath).build());
        mIsNetworkConnected = GlobalUtilities.getNetworkState();
        registerReceiver(mNetworkReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        ImageLoader.getInstance().init(GlobalUtilities.getImageConfig());
    }
    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static synchronized AppApplication getInstance() {
        return mInstance;
    }

    public boolean ismIsNetworkConnected() {
        if (!mIsNetworkConnected)
            GlobalUtilities.showNoNetworkToast();
        return mIsNetworkConnected;
    }
    public void setIsNetworkConnected(boolean isNetworkConnected) {
        this.mIsNetworkConnected = isNetworkConnected;
    }
}
