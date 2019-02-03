package com.example.deepaksharma.androidcode.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.example.deepaksharma.androidcode.R;
import com.example.deepaksharma.androidcode.global.AppApplication;
import com.example.deepaksharma.androidcode.global.constant.AppConstant;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.decode.BaseImageDecoder;

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
            Log.d(TAG, "getAddress: " + e);
        } catch (IllegalArgumentException exception) {
            exception.printStackTrace();
            Log.d(TAG, "getAddress: " + exception);
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

    //block up when loder show on screen
    public static void handleUI(Activity activity, View view, boolean isBlockUi) {
        if (isBlockUi) {
            activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            view.setVisibility(View.VISIBLE);
        } else {
            activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            view.setVisibility(View.GONE);
        }
    }

    public static void modifyDialogBounds(Activity activity, Dialog dialog) {
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(activity, android.R.color.transparent)));
        dialog.getWindow().getDecorView().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        Window window = dialog.getWindow();
        lp.copyFrom(window.getAttributes());
        //This makes the dialog take up the full width
        //lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.width = (int) (dialog.getContext().getResources().getDisplayMetrics().widthPixels * 0.83);
        //  lp.height = (int) (dialog.getContext().getResources().getDisplayMetrics().heightPixels * 0.55);
        window.setAttributes(lp);
    }

    public static ImageLoaderConfiguration getImageConfig() {
        ImageLoaderConfiguration config = null;
        if (config == null) {
            config = new ImageLoaderConfiguration.Builder(AppApplication.getInstance())
//                    .memoryCacheSize(175 * 1024)
//                    .diskCacheSize(175 * 1024)
//                    .imageDecoder(new SvgDecoder().decode())
                    .imageDecoder(new BaseImageDecoder(true))
                    .defaultDisplayImageOptions(imageLoaders())
                    .build();
        }
        return config;
    }

    public static DisplayImageOptions imageLoaders() {
        DisplayImageOptions options = null;
        if (options == null) {
            options = new DisplayImageOptions.Builder()
                    .showImageOnLoading(R.drawable.error_img)
                    .showImageOnFail(R.drawable.error_img)
                    .cacheInMemory(true)
                    .cacheOnDisk(true)
                    .build();
        }
        return options;
    }

    public static void setSpannable(TextView textView, String txtSpannable, int starText, int endText) {
        SpannableString spannableString = new SpannableString(txtSpannable);
        ForegroundColorSpan foregroundSpan = new ForegroundColorSpan(Color.GREEN);
//            BackgroundColorSpan backgroundSpan = new BackgroundColorSpan(Color.GRAY);
        spannableString.setSpan(foregroundSpan, starText, endText, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//            spannableString.setSpan(backgroundSpan, starText, endText, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(spannableString);
    }

    public static ViewDataBinding bindView(Activity activity, int custom_dialog) {
        return DataBindingUtil.inflate(LayoutInflater.from(activity), custom_dialog, null, false);
    }
    public static void btnClickAnimation(View view){
        Animation fadeAnimation = AnimationUtils.loadAnimation(view.getContext(), R.anim.fade_in);
        view.startAnimation(fadeAnimation);
    }
}
