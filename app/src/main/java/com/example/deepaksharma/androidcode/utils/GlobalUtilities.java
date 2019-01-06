package com.example.deepaksharma.androidcode.utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.location.Address;
import android.location.Geocoder;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.example.deepaksharma.androidcode.R;
import com.example.deepaksharma.androidcode.global.AppApplication;
import com.example.deepaksharma.androidcode.global.constant.AppConstant;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static android.support.constraint.Constraints.TAG;

public class GlobalUtilities {
    private static Context mContext = AppApplication.getInstance();

    public static String getDateInYYYYMMDD(String date) {
        if (date != null && date.length() > 0) {
            SimpleDateFormat sdf = new SimpleDateFormat(AppConstant.DATE_FORMAT_SRC);
            SimpleDateFormat output = new SimpleDateFormat(AppConstant.DATE_FORMAT_DST);
            Date dt = null;
            try {
                dt = sdf.parse(date);
                return output.format(dt);
            } catch (ParseException e) {
                e.printStackTrace();
                Log.d(TAG, "getDateInYYYYMMDD: " + e);
            }
        }
        return "";
    }

    public static void setDate(final TextView mDobEt) {
        DatePickerCustomDialog datePickerCustomDialog = DatePickerCustomDialog.getInstance(mContext, new DatePickerCallback() {
            @Override
            public void onDateSetCallBack(String date) {
                mDobEt.setText(date);
            }

        });
        Calendar c = Calendar.getInstance();
        int pYear = c.get(Calendar.YEAR);
        int pMonth = c.get(Calendar.MONTH);
        int pDay = c.get(Calendar.DAY_OF_MONTH);
        c.set(pYear, pMonth, pDay);
        datePickerCustomDialog.setMaxDateCustom(c.getTimeInMillis());
    }

    /**
     * convertTime formate
     *
     * @param timeHHMM
     * @return
     */
    public static String timeFormat12(String timeHHMM) {
        final String time = timeHHMM;

        try {
            final SimpleDateFormat sdf = new SimpleDateFormat("H:mm");
            final Date dateObj = sdf.parse(time);

            return new SimpleDateFormat("K:mm: a").format(dateObj);
        } catch (final ParseException e) {
            e.printStackTrace();
            Log.d(TAG, "timeFormat12: " + e);
        }
        return "";
    }

    /**
     * convertTime formate
     *
     * @param timeHHMM
     * @return
     */
    public static String timeFormat24(String timeHHMM) {
        String date = timeHHMM;
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
        Date testDate = null;
        try {
            testDate = sdf.parse(date);
        } catch (Exception ex) {
            ex.printStackTrace();
            Log.d(TAG, "timeFormat24: " + ex);
        }
        SimpleDateFormat formatter = new SimpleDateFormat("HH:MM");
        String newFormat = formatter.format(testDate);
        return newFormat;
    }
//    {START HIDE SHOW KEYBOARD}

    /**
     * Method to hide keyboard
     *
     * @param activity Context of the calling class
     */
    public static void hideKeyboard(Activity activity) {
        try {
            InputMethodManager inputManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
        } catch (Exception ignored) {
            Log.d(TAG, "hideKeyboard: " + ignored);
        }
    }
//    {END HIDE SHOW KEYBOARD}

    /**
     * Gets network state.
     *
     * @return the network state
     */
    public static boolean getNetworkState() {
        ConnectivityManager cm =
                (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
    }

    /**
     * show internet connection toast
     */
    public static void showNoNetworkToast() {
        //Toast.makeText(context.getApplicationContext(), msg, Toast.LENGTH_LONG).show();
        String msg = mContext.getResources().getString(R.string.no_network_msg);
        showToast(msg);
    }

    /**
     * show toast
     *
     * @param msg toast msg
     */
    public static void showToast(@NonNull String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }

    //    {START ACTIVITY ANIMATION}
    public static void startActivityWithLeftToRightAnimation(Activity activity, Intent in) {
        activity.startActivity(in);
        activity.overridePendingTransition(R.anim.slide_in_right, R.anim.scale_down);
    }

    public static void startActivityForResultWithLeftToRightAnimation(Fragment activity, Intent in, int req) {
        activity.startActivityForResult(in, req);
        activity.getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.scale_down);
    }

    public static void startActivityForResultWithLeftToRightAnimationA(Activity activity, Intent in, int req) {
        activity.startActivityForResult(in, req);
        activity.overridePendingTransition(R.anim.slide_in_right, R.anim.scale_down);
    }

    public static void startActivityWithZoomOut(Activity activity, Intent in) {
        activity.startActivity(in);
        activity.overridePendingTransition(R.anim.zoom_out, R.anim.slide_out_left);
    }

    public static void startActivityWithRightToLeftAnimation(Activity activity, Intent in) {
        activity.startActivity(in);
        activity.overridePendingTransition(R.anim.scale_up, R.anim.slide_out_right);
    }

    public static void startActivityForResultWithDownToUpAnimation(Activity activity, Fragment frag, Intent in, int requestCode) {
        frag.startActivityForResult(in, requestCode);
        activity.overridePendingTransition(R.anim.abc_slide_in_bottom, R.anim.stay);
    }

    public static void startActivityForResultWithDownToUpAnimation(Activity activity, Intent in, int requestCode) {
        activity.startActivityForResult(in, requestCode);
        activity.overridePendingTransition(R.anim.abc_slide_in_bottom, R.anim.abc_slide_out_bottom);
    }

    public static void startActivityWithDownToUpAnimation(Activity activity, Intent in) {
        activity.startActivity(in);
        activity.overridePendingTransition(R.anim.abc_slide_in_bottom, R.anim.abc_slide_out_bottom);
    }

    //    {END ACTIVITY ANIMATION}
//    {START SHOW DIALOG}
    public static void showOkDialog(final String message, final DialogCallBack callBack) {
        try {
            new android.app.AlertDialog.Builder(AppApplication.getInstance()).setTitle(mContext.getResources().getString(R.string.app_name))
                    .setMessage(message).setCancelable(true)
                    .setPositiveButton(mContext.getResources().getString(android.R.string.ok),
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    dialog.dismiss();
                                    dialog.dismiss();
                                    if (callBack != null) {
                                        callBack.okClick();
                                    }
                                }
                            })
                    .create().show();
        } catch (Exception e) {
            Log.e(TAG, "Showing Push Dialog Failed - " + e.getMessage());
        }
    }

    public static void showSimpleDialog(final String message, final DialogCallBack callBack) {
        try {
            new android.app.AlertDialog.Builder(mContext).setTitle(mContext.getResources().getString(R.string.app_name))
                    .setMessage(message).setCancelable(true)
                    .setPositiveButton(mContext.getResources().getString(android.R.string.ok),
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int okButton) {
                                    dialog.dismiss();
                                    dialog.dismiss();
                                    if (callBack != null) {
                                        callBack.okClick();
                                    }
                                }
                            })
                    .setNegativeButton(mContext.getResources().getString(android.R.string.cancel), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int cancelButton) {
                            callBack.cancelClick();
                        }
                    }).create().show();
        } catch (Exception e) {
            Log.e(TAG, "Showing Push Dialog Failed - " + e.getMessage());
        }
    }
    //    {END SHOW DIALOG}

    //    {START PROGRESS DIALOG}
    public static ProgressDialog showProgressDialog() {
        try {
            if (mContext != null) {
                ProgressDialog mProgressDialog = ProgressDialog.show(mContext, "", mContext.getResources().getString(R.string.lbl_please_wait));
                return mProgressDialog;
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG, "showProgressDialog: " + e);
        }
        return new ProgressDialog(mContext);
    }

    public static void hideProgressDialog(@NonNull ProgressDialog progressDialog) {
        try {
            if (progressDialog.isShowing())
                progressDialog.dismiss();
        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG, "hideProgressDialog: " + e);
        }
    }
//    {END PROGRESS DIALOG}

    /**
     * get lat long address using geocoder
     *
     * @param latitude
     * @param longitude
     * @return address
     */
    public static String getAddress(String latitude, String longitude) {
        Geocoder geocoder;
        List<Address> addresses;
        String address = "";
        geocoder = new Geocoder(mContext.getApplicationContext(), Locale.getDefault());

        try {
            addresses = geocoder.getFromLocation(Double.valueOf(latitude), Double.valueOf(longitude), 1);
            if (addresses.size() > 0)
                address = addresses.get(0).getAddressLine(0);
        } catch (IOException e) {
            e.printStackTrace();
            Log.d(TAG, "getAddress: "+e);
        } catch (IllegalArgumentException exception) {
            exception.printStackTrace();
            Log.d(TAG, "getAddress: "+exception);
        }
        return address;
    }

    /**
     * get distance between two lat long
     *
     * @param currlat
     * @param currlng
     * @param givenlat
     * @param givenlng
     * @return distane in miles
     */
    public static double checkdistance(double currlat, double currlng, double givenlat, double givenlng) {

        double earthRadius = 3958.75; // in miles, change to 6371 for kilometer output

        double dLat = Math.toRadians(givenlat - currlat);
        double dLng = Math.toRadians(givenlng - currlng);

        double sindLat = Math.sin(dLat / 2);
        double sindLng = Math.sin(dLng / 2);

        double a = Math.pow(sindLat, 2) + Math.pow(sindLng, 2)
                * Math.cos(Math.toRadians(currlat)) * Math.cos(Math.toRadians(givenlat));

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        double dist = earthRadius * c;

        return dist; // output distance, in MILES
    }
//    {START SHOW IMAGE}

    /**
     * show image from url in circle
     *
     * @param imgUrl   image url
     * @param targetIv image view
     */
    public static void showCircularImageUsingUrl(String imgUrl, ImageView targetIv) {
        Glide.with(mContext)
                .load(imgUrl).asBitmap()
                .error(R.drawable.logo)
                .placeholder(R.drawable.logo)
                .into(new BitmapImageViewTarget(targetIv) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable circularBitmapDrawable =
                                RoundedBitmapDrawableFactory.create(mContext.getResources(), resource);
                        circularBitmapDrawable.setCircular(true);
                        targetIv.setImageDrawable(circularBitmapDrawable);
                    }
                });
    }

    /**
     * show image from url
     *
     * @param imgUrl   image url
     * @param targetIv image view
     */
    public static void showImageUsingUrl(String imgUrl, final ImageView targetIv) {
        Glide.with(mContext)
                .load(imgUrl).asBitmap()
                .error(R.drawable.logo)
                .placeholder(R.drawable.logo)
                .into(targetIv);
    }
//    {END SHOW IMAGE}


}