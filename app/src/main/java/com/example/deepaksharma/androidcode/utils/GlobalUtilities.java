package com.example.deepaksharma.androidcode.utils;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.example.deepaksharma.androidcode.R;
import com.example.deepaksharma.androidcode.global.AppApplication;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Modifier;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;


public class GlobalUtilities {

    private static Context mContext = AppApplication.getInstance();
    /**
     * set date in textview
     *
     * @param mDobEt
     */
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
        datePickerCustomDialog.show();
    }

    public static void getDate(Context context, TextView mDobEtm) {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(context, R.style.TimePicker, (view, year, month, dayOfMonth) -> {
            int monthValue = month + 1;
            String day = "";
            String dayMonth = "";
            if (dayOfMonth<=9) day= "0"+dayOfMonth;
            else day = dayOfMonth+"";
            if (monthValue<=9) dayMonth= "0"+monthValue;
            else dayMonth = monthValue+"";
            mDobEtm.setText(day +"/"+dayMonth+"/"+year);
        }, calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH) , calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
        datePickerDialog.show();
    }

    /**
     * convert date formate
     *
     * @param date         date any formate string
     * @param inputFormat  input date formate
     * @param outputFormat output date formate
     * @return output date formate
     */
    public static String dateFormate(String date, String inputFormat, String outputFormat) {
        Date initDate = null;
        try {
            initDate = new SimpleDateFormat(inputFormat).parse(date);
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat formatter = new SimpleDateFormat(outputFormat);
        return formatter.format(initDate);
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

    /**
     * show string in different color using spannable
     *
     * @param textView     view
     * @param txtSpannable string text
     * @param starText     start index of text
     * @param endText      end index of text
     */
    public static void setSpannable(TextView textView, String txtSpannable, int starText, int endText) {
        SpannableString spannableString = new SpannableString(txtSpannable);
        ForegroundColorSpan foregroundSpan = new ForegroundColorSpan(Color.GREEN);
//            BackgroundColorSpan backgroundSpan = new BackgroundColorSpan(Color.GRAY);
        spannableString.setSpan(foregroundSpan, starText, endText, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//            spannableString.setSpan(backgroundSpan, starText, endText, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(spannableString);
    }

    /**
     * button click fade animation
     *
     * @param view view reference
     */
    public static void btnClickAnimation(View view) {
        Animation fadeAnimation = AnimationUtils.loadAnimation(view.getContext(), R.anim.fade_in);
        view.startAnimation(fadeAnimation);
    }

    /**
     * provide binding of layout
     *
     * @param context reference of activity
     * @param layout  layout
     * @return viewBinding
     */
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

    /**
     * two digit random number
     *
     * @return random number
     */
    public static int getTwoDigitRandomNo() {
        return new Random().nextInt(90) + 10;
    }

    public static void changeLanguage(Context context, String languageToLoad) {
        Locale locale = new Locale(languageToLoad);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        context.getResources().updateConfiguration(config,
                context.getResources().getDisplayMetrics());
    }
}
