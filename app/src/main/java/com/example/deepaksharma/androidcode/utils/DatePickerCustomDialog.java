package com.example.deepaksharma.androidcode.utils;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.text.format.DateFormat;
import android.widget.DatePicker;

import com.example.deepaksharma.androidcode.R;
import com.example.deepaksharma.androidcode.global.constant.AppConstant;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class DatePickerCustomDialog extends DatePickerDialog {

    private static DatePickerCustomDialog mDatePicker;
    static long maxDate = -1;
    static long minDate = -1;

    static boolean isMinMaxDateSet = true;

    public static void setIsMinMaxDateSet(boolean isMinMaxDateSet) {
        DatePickerCustomDialog.isMinMaxDateSet = isMinMaxDateSet;
    }

    private DatePickerCustomDialog(Context context, OnDateSetListener callBack, int year, int monthOfYear, int dayOfMonth) {
        super(context, callBack, year, monthOfYear, dayOfMonth);
        maxDate = -1;
        minDate = -1;
    }


    public static DatePickerCustomDialog getInstance(final Context mContext, final DatePickerCallback callBack) {
        Calendar mcurrentDate = Calendar.getInstance();
        int mYear = mcurrentDate.get(Calendar.YEAR);
        int mMonth = mcurrentDate.get(Calendar.MONTH);
        int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);
        mDatePicker = new DatePickerCustomDialog(mContext, new OnDateSetListener() {

            public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(selectedyear, selectedmonth, selectedday);
                SimpleDateFormat dateFormatter = new SimpleDateFormat(AppConstant.DATE_FORMAT_Y_M_D, Locale.US);

                if (callBack != null) {


                    if (isMinMaxDateSet) {
                        // if (compareWithMinDate(newDate.getTimeInMillis()) || compareWithMaxDate(newDate.getTimeInMillis())) {
                        callBack.onDateSetCallBack(dateFormatter.format(newDate.getTime()));
                        // }

                    } else {
                        callBack.onDateSetCallBack(dateFormatter.format(newDate.getTime()));
                    }
                }

            }
        }, mYear, mMonth, mDay);
//        mDatePicker.setTitle("Select date");

      /*  if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.HONEYCOMB) {
            mDatePicker.getDatePicker().setMaxDate(mcurrentDate.getTime().getTime());
        }*/
        mDatePicker.show();
        return mDatePicker;
    }

    private static boolean compareWithMinDate(long selectedTime) {
        if (getMinDate() != -1) {

            Calendar calendar1 = Calendar.getInstance();
            calendar1.setTimeInMillis(selectedTime);
            int day1 = calendar1.get(Calendar.DAY_OF_MONTH);
            int month1 = calendar1.get(Calendar.MONTH);
            int year1 = calendar1.get(Calendar.YEAR);

            String dayString1 = String.valueOf(day1);
            String monthString1 = String.valueOf(month1 + 1);
            String yearString1 = String.valueOf(year1);

            Calendar calendar2 = Calendar.getInstance();
            calendar2.setTimeInMillis(getMinDate());
            int day2 = calendar2.get(Calendar.DAY_OF_MONTH);
            int month2 = calendar2.get(Calendar.MONTH);
            int year2 = calendar2.get(Calendar.YEAR);

            String dayString2 = String.valueOf(day2);
            String monthString2 = String.valueOf(month2 + 1);
            String yearString2 = String.valueOf(year2);
            try {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(AppConstant.DATE_FORMAT1);
                Date date1 = simpleDateFormat.parse(monthString1 + "-" + dayString1 + "-" + yearString1);
                Date date2 = simpleDateFormat.parse(monthString2 + "-" + dayString2 + "-" + yearString2);

                return !date1.before(date2);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    private static boolean compareWithMaxDate(long selectedTime) {
        if (getMaxDate() != -1) {
            Calendar calendar1 = Calendar.getInstance();
            calendar1.setTimeInMillis(selectedTime);
            int day1 = calendar1.get(Calendar.DAY_OF_MONTH);
            int month1 = calendar1.get(Calendar.MONTH);
            int year1 = calendar1.get(Calendar.YEAR);

            String dayString1 = String.valueOf(day1);
            String monthString1 = String.valueOf(month1 + 1);
            String yearString1 = String.valueOf(year1);

            Calendar calendar2 = Calendar.getInstance();
            calendar2.setTimeInMillis(getMaxDate());
            int day2 = calendar2.get(Calendar.DAY_OF_MONTH);
            int month2 = calendar2.get(Calendar.MONTH);
            int year2 = calendar2.get(Calendar.YEAR);

            String dayString2 = String.valueOf(day2);
            String monthString2 = String.valueOf(month2 + 1);
            String yearString2 = String.valueOf(year2);
            try {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(AppConstant.DATE_FORMAT1);
                Date date1 = simpleDateFormat.parse(monthString1 + "-" + dayString1 + "-" + yearString1);
                Date date2 = simpleDateFormat.parse(monthString2 + "-" + dayString2 + "-" + yearString2);

                return !date1.after(date2);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public void setMaxDate() {
        mDatePicker.getDatePicker().setMaxDate(System.currentTimeMillis());
        maxDate = mDatePicker.getDatePicker().getMaxDate();
    }

    public void setMaxDateCustom(long timeInMillis) {
        mDatePicker.getDatePicker().setMaxDate(timeInMillis);
        maxDate = mDatePicker.getDatePicker().getMaxDate();
    }


    public static long getMaxDate() {
        return maxDate;
    }

    public static long getMinDate() {
        return minDate;
    }

    public void setMinDate() {
        mDatePicker.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        minDate = mDatePicker.getDatePicker().getMinDate();
    }

    public void setMinDateWithAfterOneDay(String date) {
        String[] fulldate = date.split("-");
        Calendar mcurrentDate = Calendar.getInstance();
        mcurrentDate.set(Calendar.YEAR, Integer.parseInt(fulldate[0]));
        mcurrentDate.set(Calendar.MONTH, Integer.parseInt(fulldate[1]) - 1);
        mcurrentDate.set(Calendar.DAY_OF_MONTH, Integer.parseInt(fulldate[2])/* + 1*/);
        mDatePicker.getDatePicker().setMinDate(mcurrentDate.getTime().getTime() - 1000);
        minDate = mDatePicker.getDatePicker().getMinDate();
    }

    public static TimePickerDialog getTime(Context context, TimePickerDialog.OnTimeSetListener timeListener) {
        Calendar calendar = Calendar.getInstance();
//        new TimePickerDialog(getActivity(), timeListener, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), false).show();
        return new TimePickerDialog(context, R.style.TimePicker, timeListener, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE),
                DateFormat.is24HourFormat(context));
    }

    public static DatePickerDialog getDate(Context context, OnDateSetListener dateListener) {
        Calendar calendar = Calendar.getInstance();
        return new DatePickerDialog(context, R.style.TimePicker, dateListener, calendar.YEAR, calendar.MONTH, calendar.DAY_OF_MONTH);
    }

}
