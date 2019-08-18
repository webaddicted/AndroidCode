package com.example.deepaksharma.androidcode.global;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;


import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.deepaksharma.androidcode.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Deepak Sharma
 */
public class PermissionsHandler {
    private static final int PERMISSION_CODE = 1212;
    private static List<String> mCustomPermission = null;
    private static PermissionListener mPerpermissionListener;

    /**
     * Check if version is marshmallow and above.
     * Used in deciding to ask runtime permission
     * NO of permission check a at time.
     *
     * @param permissionListener is describe permission status
     * @param permissions        is bundle of all permission
     */
    public static boolean requestMultiplePermission(Activity activity, @NonNull List<String> permissions, @NonNull PermissionListener permissionListener) {
        mPerpermissionListener = permissionListener;
        mCustomPermission = permissions;
        if (Build.VERSION.SDK_INT >= 23) {
            List<String> listPermissionsNeeded = permissions;
            List<String> listPermissionsAssign = new ArrayList<>();
            for (String per : listPermissionsNeeded) {
                if (ContextCompat.checkSelfPermission(activity.getApplicationContext(), per) != PackageManager.PERMISSION_GRANTED) {
                    listPermissionsAssign.add(per);
                }
            }
            if (!listPermissionsAssign.isEmpty()) {
                ActivityCompat.requestPermissions(activity, listPermissionsAssign.toArray(new String[listPermissionsAssign.size()]), PERMISSION_CODE);
                return false;
            }
        }
        return true;
    }

    /**
     * Check if version is marshmallow and above.
     * Used in deciding to ask runtime permission
     * check single permission
     *
     * @param permissionListener is describe permission status
     * @param permissions        is single permission
     */
    public static boolean requestSinglePermission(Activity activity,@NonNull String permissions, @NonNull PermissionListener permissionListener) {
        mPerpermissionListener = permissionListener;
        mCustomPermission = Arrays.asList(new String[]{permissions});
        if (Build.VERSION.SDK_INT >= 23) {
            if (ActivityCompat.checkSelfPermission(activity, permissions) != PackageManager.PERMISSION_GRANTED) {
//                askRequestPermissions(new String[]{permissions});
                ActivityCompat.requestPermissions(activity, new String[]{permissions}, PERMISSION_CODE);
                return false;
            }
        }
        return true;
    }

    public static void onRequestPermissionsResult(Activity activity, @NonNull int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_CODE: {
                List<String> listPermissionsNeeded = mCustomPermission;
                Map<String, Integer> perms = new HashMap<>();
                for (String permission : listPermissionsNeeded) {
                    perms.put(permission, PackageManager.PERMISSION_GRANTED);
                }
                if (grantResults.length > 0) {
                    for (int i = 0; i < permissions.length; i++)
                        perms.put(permissions[i], grantResults[i]);
                    boolean isAllGranted = true;
                    for (String permission : listPermissionsNeeded) {
                        if (perms.get(permission) == PackageManager.PERMISSION_DENIED) {
                            isAllGranted = false;
                            break;
                        }
                    }
                    if (isAllGranted) {
                        mPerpermissionListener.onPermissionGranted(mCustomPermission);
                    } else {
                        boolean shouldRequest = false;
                        for (String permission : listPermissionsNeeded) {
                            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)) {
                                shouldRequest = true;
                                break;
                            }
                        }
                        if (shouldRequest) {
                            ifCancelledAndCanRequest(activity);
                        } else {
                            //permission is denied (and never ask again is  checked)
                            //shouldShowRequestPermissionRationale will return false
                            ifCancelledAndCannotRequest(activity);
                        }
                    }
                }
            }
        }
    }

    /**
     * permission cancel dialog
     */
    private static void ifCancelledAndCanRequest(Activity activity) {
        showDialogOK(activity, "Permission required for this app, please grant all permission .", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        requestMultiplePermission(activity,mCustomPermission, mPerpermissionListener);
                        break;
                    case DialogInterface.BUTTON_NEGATIVE:
                        mPerpermissionListener.onPermissionDenied(mCustomPermission);
                        // proceed with logic by disabling the related features or quit the app.
                        break;
                }
            }
        });
    }

    /**
     * forcefully stoped all permission dialog
     */
    private static void ifCancelledAndCannotRequest(Activity mActivity) {
        showDialogOK(mActivity, mActivity.getResources().getString(R.string.forcefully_enable_permission), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        Intent intent = new Intent();
                        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        Uri uri = Uri.fromParts("package", mActivity.getPackageName(), null);
                        intent.setData(uri);
                        mActivity.startActivity(intent);
                        break;
                    case DialogInterface.BUTTON_NEGATIVE:
                        mPerpermissionListener.onPermissionDenied(mCustomPermission);
                        // proceed with logic by disabling the related features or quit the app.
                        break;
                }
            }
        });
    }

    private static void showDialogOK(Activity activity, String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(activity).setMessage(message).setPositiveButton(activity.getResources().getString(R.string.ok),
                okListener).setNegativeButton(activity.getResources().getString(R.string.cancel), okListener).create().show();
    }

    /**
     * Check multiple permission if version is marshmallow and above.
     * result in form of boolean
     *
     * @param permissions is bundle of all permission
     */
    public static boolean checkMultiplePermission(Activity mActivity,@NonNull List<String> permissions) {
        mCustomPermission = permissions;
        if (Build.VERSION.SDK_INT >= 23) {
            List<String> listPermissionsNeeded = permissions;
            List<String> listPermissionsAssign = new ArrayList<>();
            for (String per : listPermissionsNeeded) {
                if (ContextCompat.checkSelfPermission(mActivity.getApplicationContext(), per) != PackageManager.PERMISSION_GRANTED) {
                    listPermissionsAssign.add(per);
                }
            }
            if (!listPermissionsAssign.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Check if version is marshmallow and above.
     * Used in deciding to ask runtime permission
     * check single permission
     *
     * @param permissions is single permission
     */
    public static boolean checkPermission(Activity mActivity,@NonNull String permissions) {
        mCustomPermission = Arrays.asList(new String[]{permissions});
        if (Build.VERSION.SDK_INT >= 23) {
            if (ActivityCompat.checkSelfPermission(mActivity, permissions) != PackageManager.PERMISSION_GRANTED)
                return false;
        }
        return true;
    }


    public interface PermissionListener {
        void onPermissionGranted(List<String> mCustomPermission);

        void onPermissionDenied(List<String> mCustomPermission);
    }

    public static void clearPermission() {
        mCustomPermission = null;
        mPerpermissionListener = null;
    }
}