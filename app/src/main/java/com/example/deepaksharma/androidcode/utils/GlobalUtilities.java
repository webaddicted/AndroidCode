package com.example.deepaksharma.androidcode.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.example.deepaksharma.androidcode.global.FileUtils;
import com.example.deepaksharma.androidcode.global.constant.AppConstant;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.decode.BaseImageDecoder;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Modifier;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

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

    /***
     * Show SoftInput Keyboard
     * @param activity reference of current activity
     */
    public static void showKeyboard(Activity activity) {
        if (activity != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null)
                imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
        }
    }

//    {END HIDE SHOW KEYBOARD}


//      {START STRING TO JSON & JSON TO STRING}

    /**
     * @param json  json String converted by Gson to string
     * @param clazz referance of class type like MyBean.class
     * @param <T>
     * @return bean referance
     */
    public static <T> T stringToJson(String json, Class<T> clazz) {
        return new GsonBuilder()
                .excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC)
                .create().fromJson(json, clazz);
    }

    /**
     * @param clazz referance of any bean
     * @return
     */
    public static String jsonToString(Class clazz) {
        return new Gson().toJson(clazz);
    }

    //{END STRING TO JSON & JSON TO STRING}

    /**
     * Gets network state.
     *
     * @return the network state
     */
    public static boolean isNetworkAvailable() {
        ConnectivityManager connMgr = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = connMgr.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isAvailable() && activeNetwork.isConnected();
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

    //block up when loder show on screen

    /**
     * handle ui
     *
     * @param activity
     * @param view
     * @param isBlockUi
     */
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

    public static void btnClickAnimation(View view) {
        Animation fadeAnimation = AnimationUtils.loadAnimation(view.getContext(), R.anim.fade_in);
        view.startAnimation(fadeAnimation);
    }

    public static ViewDataBinding getLayoutBinding(Context context, int layout) {
        return DataBindingUtil.
                inflate(LayoutInflater.from(context),
                        layout,
                        null, false);
    }
    /**
     * @param sizeOfRandomString length of random string
     * @return generate a random string
     */
    public static String getRandomString(final int sizeOfRandomString) {
        String ALLOWED_CHARACTERS = "0123456789qwertyuiopasdfghjklzxcvbnm";
        final Random random = new Random();
        final StringBuilder sb = new StringBuilder(sizeOfRandomString);
        for (int i = 0; i < sizeOfRandomString; ++i)
            sb.append(ALLOWED_CHARACTERS.charAt(random.nextInt(ALLOWED_CHARACTERS.length())));
        return sb.toString();
    }
public static int getTwoDigitRandomNo(){
    return new Random().nextInt(90) + 10;
}

}
