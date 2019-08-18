package com.example.deepaksharma.androidcode.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

public class ReferralCodeReciever extends BroadcastReceiver {
    public static final String TAG = DeviceBootReceiver.class.getSimpleName();
    public static final String ACTION_UPDATE_DATA = "ACTION_UPDATE_DATA";
    private static final String ACTION_INSTALL_REFERRER = "com.android.vending.INSTALL_REFERRER";
    private static final String KEY_REFERRER = "referrer";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReceive: " + intent.getExtras().toString());
        if (intent == null) return;
        if (!ACTION_INSTALL_REFERRER.equals(intent.getAction())) {
            Log.d(TAG, "Wrong action! Expected: " + ACTION_INSTALL_REFERRER + " but was: " + intent.getAction());
            return;
        }
        if (intent.getExtras() == null) {
            Log.d(TAG, "No data in intent");
            return;
        } else {
            if (!(intent.getExtras().getString(KEY_REFERRER)).equals("utm_source=google-play&utm_medium=organic")) {
                Log.d(TAG, intent.getExtras().getString(KEY_REFERRER) + "\n " + intent.getExtras().get(KEY_REFERRER));
            } else {
                Log.d(TAG, intent.getExtras().getString(KEY_REFERRER));
            }
        }
        LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent(ACTION_UPDATE_DATA));
    }
}