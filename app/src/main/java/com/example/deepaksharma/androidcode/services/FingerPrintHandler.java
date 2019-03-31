package com.example.deepaksharma.androidcode.services;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.CancellationSignal;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by DeepakSharma on 24/03/18.
 */

/*  Implement Fingerprint Authentication Callback to get access to Fingerprint methods  */
@TargetApi(Build.VERSION_CODES.M)
public class FingerPrintHandler extends FingerprintManager.AuthenticationCallback {

    private Context context;
    private TextView errorText;

    public FingerPrintHandler(Context mContext, TextView errorText) {
        context = mContext;
        this.errorText=errorText;
    }


    public void startAuth(FingerprintManager manager, FingerprintManager.CryptoObject cryptoObject) {
        CancellationSignal cancellationSignal = new CancellationSignal();
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        manager.authenticate(cryptoObject, cancellationSignal, 0, this, null);
    }

    /*  Method will call on Fingerprint Auth Error  */
    @Override
    public void onAuthenticationError(int errMsgId, CharSequence errString) {
        this.update("Fingerprint Authentication error\n" + errString);
    }

    /*  Method will call on Fingerprint Auth have some help  */
    @Override
    public void onAuthenticationHelp(int helpMsgId, CharSequence helpString) {
        this.update("Fingerprint Authentication help\n" + helpString);
    }

    /*  Method will call on Fingerprint Auth Failed  */
    @Override
    public void onAuthenticationFailed() {
        this.update("Fingerprint Authentication failed. Please try again.");
    }

    /*  Method will call on Fingerprint Auth Succeeded  */
    @Override
    public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
        Log.d("Authentication", "Fingerprint Authentication successful.");
        onAuthSuccess();
    }

    /*  Trigger this method on FingerPrint get Success  */
    private void onAuthSuccess() {
        Toast.makeText(context, " FingerPrint Success Match", Toast.LENGTH_SHORT).show();
//        ((AppCompatActivity) context).finish();
    }


    /*  Method to update Error text message on Auth failed  */
    public void update(String e) {
        errorText.setText(e);
    }
}