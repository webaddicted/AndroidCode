package com.example.deepaksharma.androidcode.view.fragment;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.KeyguardManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.Bundle;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyPermanentlyInvalidatedException;
import android.security.keystore.KeyProperties;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.databinding.ViewDataBinding;

import com.example.deepaksharma.androidcode.R;
import com.example.deepaksharma.androidcode.databinding.FragmentFingerPrintBinding;
import com.example.deepaksharma.androidcode.global.PermissionsHandler;
import com.example.deepaksharma.androidcode.services.FingerPrintHandler;
import com.example.deepaksharma.androidcode.utils.GlobalUtilities;
import com.example.deepaksharma.androidcode.view.base.BaseFragment;
import com.example.deepaksharma.androidcode.view.home.HomeActivity;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

import static android.content.Context.FINGERPRINT_SERVICE;
import static android.content.Context.KEYGUARD_SERVICE;

public class FingerPrintFragment extends BaseFragment {
    public static final String TAG = FingerPrintFragment.class.getSimpleName();
    private FragmentFingerPrintBinding mBinding;
    private FingerprintManager mFingerprintMgr;
    private static final String KEY_NAME = "AndroHub";
    private KeyStore mKeyStore;
    private Cipher mCipher;

    public static FingerPrintFragment getInstance(Bundle bundle) {
        FingerPrintFragment fragment = new FingerPrintFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_finger_print;
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onViewsInitialized(ViewDataBinding binding, View view) {
        mBinding = (FragmentFingerPrintBinding) binding;
        init();
        clickListener();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void init() {
        KeyguardManager keyguardManager = (KeyguardManager) getActivity().getSystemService(KEYGUARD_SERVICE);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1)
            mFingerprintMgr = (FingerprintManager) getActivity().getSystemService(FINGERPRINT_SERVICE);
        if (mFingerprintMgr == null) {
            GlobalUtilities.showToast("Fingerprint manager not support.");
            return;
        }
        if (mFingerprintMgr != null && !mFingerprintMgr.isHardwareDetected()) {
            mBinding.txtErrorMessage.setText(getResources().getString(R.string.fingerprint_not_exist));
        } else {
            if (!PermissionsHandler.checkPermission(getActivity(),Manifest.permission.USE_FINGERPRINT)) {
                mBinding.txtErrorMessage.setText(getResources().getString(R.string.fingerprint_not_enabled));
            } else {
                if (mFingerprintMgr != null && !mFingerprintMgr.hasEnrolledFingerprints()) {
                    mBinding.txtErrorMessage.setText(getResources().getString(R.string.fingerprint_not_registered));
                } else {
                    // Checks whether lock screen security is enabled or not
                    if (!keyguardManager.isKeyguardSecure()) {
                        //Show error message when screen security is disabled
                        mBinding.txtErrorMessage.setText(getResources().getString(R.string.lock_screen_setting_disabled));
                    } else {
                        //else generate keystore key
                        generateKey();
                        //Now initiate Cipher, if mCipher is initiated successfully then proceed
                        if (cipherInit()) {
                            FingerprintManager.CryptoObject cryptoObject = new FingerprintManager.CryptoObject(mCipher);
                            FingerPrintHandler helper = new FingerPrintHandler(getActivity(), mBinding.txtErrorMessage);//Set Fingerprint Handler class
                            helper.startAuth(mFingerprintMgr, cryptoObject);//now start authentication process
                        }
                    }
                }
            }
        }
    }

    private void clickListener() {
        mBinding.btnFingerPrint.setOnClickListener(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.btn_finger_print:
                init();
                break;
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        ((HomeActivity) getActivity()).showBackBtn();
        ((HomeActivity) getActivity()).setToolbarTitle(getResources().getString(R.string.animation_title));
    }

    @TargetApi(Build.VERSION_CODES.M)
    protected void generateKey() {
        try {
            // Get the reference to the key store
            mKeyStore = KeyStore.getInstance("AndroidKeyStore");
        } catch (Exception e) {
            e.printStackTrace();
        }

        KeyGenerator keyGenerator;
        try {
            // Key generator to generate the key
            keyGenerator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, "AndroidKeyStore");
        } catch (NoSuchAlgorithmException | NoSuchProviderException e) {
            throw new RuntimeException("Failed to get KeyGenerator instance", e);
        }

        try {
            mKeyStore.load(null);
            keyGenerator.init(new
                    KeyGenParameterSpec.Builder(KEY_NAME,
                    KeyProperties.PURPOSE_ENCRYPT |
                            KeyProperties.PURPOSE_DECRYPT)
                    .setBlockModes(KeyProperties.BLOCK_MODE_CBC)
                    .setUserAuthenticationRequired(true)
                    .setEncryptionPaddings(
                            KeyProperties.ENCRYPTION_PADDING_PKCS7)
                    .build());
            keyGenerator.generateKey();
        } catch (NoSuchAlgorithmException |
                InvalidAlgorithmParameterException
                | CertificateException | IOException e) {
            throw new RuntimeException(e);
        }
    }


    @TargetApi(Build.VERSION_CODES.M)
    public boolean cipherInit() {
        try {
            mCipher = Cipher.getInstance(KeyProperties.KEY_ALGORITHM_AES + "/" + KeyProperties.BLOCK_MODE_CBC + "/" + KeyProperties.ENCRYPTION_PADDING_PKCS7);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            throw new RuntimeException("Failed to get Cipher", e);
        }

        try {
            mKeyStore.load(null);
            SecretKey key = (SecretKey) mKeyStore.getKey(KEY_NAME,
                    null);
            mCipher.init(Cipher.ENCRYPT_MODE, key);
            return true;
        } catch (KeyPermanentlyInvalidatedException e) {
            return false;
        } catch (KeyStoreException | CertificateException | UnrecoverableKeyException | IOException | NoSuchAlgorithmException | InvalidKeyException e) {
            throw new RuntimeException("Failed to init Cipher", e);
        }

    }
}
