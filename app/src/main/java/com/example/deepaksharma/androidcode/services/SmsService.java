package com.example.deepaksharma.androidcode.services;

import android.app.IntentService;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;

import com.example.deepaksharma.androidcode.global.AppApplication;
import com.example.deepaksharma.androidcode.model.SmsBean;
import com.example.deepaksharma.androidcode.model.eventBus.EventSmsListener;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

public class SmsService  extends IntentService {
    public static final String TAG = GetAllImageService.class.getSimpleName();

    public SmsService() {
        super("SmsService");
    }
    @Override
    protected void onHandleIntent(Intent intent) {
        EventBus.getDefault().post(new EventSmsListener(getAllSms(AppApplication.getInstance())));
    }




    public List<SmsBean> getAllSms(Context context) {
        List<SmsBean> smsBeanList = new ArrayList<SmsBean>();
        SmsBean objSms = new SmsBean();
        Uri message = Uri.parse("content://sms/");
        ContentResolver contentResolver = context.getContentResolver();

        Cursor cursor = contentResolver.query(message, null, null, null, null);
        int totalSMS = cursor.getCount();

        if (cursor.moveToFirst()) {
            for (int i = 0; i < totalSMS; i++) {

                objSms = new SmsBean();
//                objSms.setId(cursor.getString(cursor.getColumnIndexOrThrow("_id")));
//                objSms.setAddress(cursor.getString(cursor
//                        .getColumnIndexOrThrow("address")));
//                objSms.setMsg(cursor.getString(cursor.getColumnIndexOrThrow("body")));
//                objSms.setReadState(cursor.getString(cursor.getColumnIndex("read")));
//                objSms.setTime(cursor.getString(cursor.getColumnIndexOrThrow("date")));
//                if (cursor.getString(cursor.getColumnIndexOrThrow("type")).contains("1")) {
//                    objSms.setFolderName("inbox");
//                } else {
//                    objSms.setFolderName("sent");
//                }

                smsBeanList.add(objSms);
                cursor.moveToNext();
            }
        }
        // else {
        // throw new RuntimeException("You have no SMS");
        // }
        cursor.close();

        return smsBeanList;
    }}
