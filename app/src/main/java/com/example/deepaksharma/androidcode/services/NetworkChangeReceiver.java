package com.example.deepaksharma.androidcode.services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.deepaksharma.androidcode.R;
import com.example.deepaksharma.androidcode.global.AppApplication;
import com.example.deepaksharma.androidcode.model.NetworkListenerBean;
import com.example.deepaksharma.androidcode.utils.GlobalUtilities;

import org.greenrobot.eventbus.EventBus;

import static android.support.constraint.Constraints.TAG;

/**
 * Created by Deepak Sharma on 06/01/19.
 */

/**
 * The type Network change receiver.
 */
public class NetworkChangeReceiver extends BroadcastReceiver {
private static final String TAG = NetworkChangeReceiver.class.getSimpleName();
    /**
     * The constant connectivityReceiverListener.
     */
    public static ConnectivityReceiverListener connectivityReceiverListener;

    /**
     * Instantiates a new Network change receiver.
     */
    public NetworkChangeReceiver() {
        super();
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("TAG", "onReceive: ");
        if (GlobalUtilities.getNetworkState()) {
            EventBus.getDefault().post(new NetworkListenerBean(true));
            GlobalUtilities.showToast(context.getResources().getString(R.string.internet_connected));
            if (connectivityReceiverListener != null)
                connectivityReceiverListener.onNetworkConnectionChanged(true);
        } else {
            AppApplication.getInstance().setIsNetworkConnected(false);
            EventBus.getDefault().post(new NetworkListenerBean(false));
            GlobalUtilities.showToast(context.getResources().getString(R.string.no_network_msg));
            if (connectivityReceiverListener != null)
                connectivityReceiverListener.onNetworkConnectionChanged(false);
        }
    }


    /**
     * The interface Connectivity receiver listener.
     */
    public interface ConnectivityReceiverListener {
        /**
         * This method is invoked bu receiver when internet connection enables or disables.
         *
         * @param isConnected network connectivity status.
         */
        void onNetworkConnectionChanged(boolean isConnected);
    }

}
