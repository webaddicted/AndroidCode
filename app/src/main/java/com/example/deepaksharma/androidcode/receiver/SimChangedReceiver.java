package com.example.deepaksharma.androidcode.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class SimChangedReceiver extends BroadcastReceiver {
    private static final String TAG = SimChangedReceiver.class.getSimpleName();
    private static final String EXTRA_SIM_STATE = "ss";

    @Override
    public void onReceive(final Context context, final Intent intent) {
        Log.d(TAG, "onResponse :onReceive:  sim Changed===>" + intent.getExtras());
        String state = intent.getExtras().getString(EXTRA_SIM_STATE);
        if (state != null) {
            switch (state) {
                case "ABSENT":
                    Log.d(TAG, "onReceive: ABSENT");
                    break;
                case "NOT_READY":
                    Log.d(TAG, "onReceive: NOT_READY");
                    break;
                case "UNKNOWN":
                    Log.d(TAG, "onReceive: UNKNOWN");
                    break;
                case "READY":
                    Log.d(TAG, "onReceive: READY");
                    break;
                case "IMSI":
                    Log.d(TAG, "onReceive: IMSI");
                    break;
                case "LOADED":
                    Log.d(TAG, "onReceive: LOADED");
                    break;
                case "NETWORK_LOCKED":
                    Log.d(TAG, "onReceive: NETWORK_LOCKED");
                    break;
            }
        }
    }
}