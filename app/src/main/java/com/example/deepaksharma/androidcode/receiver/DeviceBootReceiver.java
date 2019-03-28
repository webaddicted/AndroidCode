package com.example.deepaksharma.androidcode.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

public class DeviceBootReceiver extends BroadcastReceiver {
    String TAG = DeviceBootReceiver.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
//        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
        if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) {
            int currentapiVersion = android.os.Build.VERSION.SDK_INT;
            Log.d(TAG, "onResponse: onReceive:  currentapiVersion===>" + currentapiVersion +
                    "\n Build.MANUFACTURER===>" + Build.MANUFACTURER +
                    "\n android.os.Build.VERSION_CODES.LOLLIPOP===>" + android.os.Build.VERSION_CODES.LOLLIPOP_MR1);
        }
    }

}