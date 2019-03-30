package com.example.deepaksharma.androidcode.services;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.CallLog;
import android.provider.ContactsContract;

import com.example.deepaksharma.androidcode.R;
import com.example.deepaksharma.androidcode.global.AppApplication;
import com.example.deepaksharma.androidcode.model.common.CallLogBean;
import com.example.deepaksharma.androidcode.model.common.ContactsBean;
import com.example.deepaksharma.androidcode.model.eventBus.EventCallLogListener;
import com.example.deepaksharma.androidcode.model.eventBus.EventContactListener;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CallLogService extends IntentService {
    public static final String TAG = CallLogService.class.getSimpleName();

    public CallLogService() {
        super("CallLogService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        EventBus.getDefault().post(new EventCallLogListener(getAllCallLog(AppApplication.getInstance())));
    }

    private static List<CallLogBean> getAllCallLog(Context context) {
        List<CallLogBean>callLogBeanList = new ArrayList<>();
        StringBuffer stringBuffer = new StringBuffer();
        Cursor cursor = context.getContentResolver().query(CallLog.Calls.CONTENT_URI,
                null, null, null, CallLog.Calls.DATE + " DESC");
        int id = cursor.getColumnIndex(CallLog.Calls._ID);
        int count = cursor.getColumnIndex(CallLog.Calls._COUNT);
        int name = cursor.getColumnIndex(CallLog.Calls.CACHED_NAME);
        int photoUri = cursor.getColumnIndex(CallLog.Calls.CACHED_PHOTO_URI);
        int isRead = cursor.getColumnIndex(CallLog.Calls.IS_READ);
        int number = cursor.getColumnIndex(CallLog.Calls.NUMBER);
        int type = cursor.getColumnIndex(CallLog.Calls.TYPE);
        int date = cursor.getColumnIndex(CallLog.Calls.DATE);
        int duration = cursor.getColumnIndex(CallLog.Calls.DURATION);
        while (cursor.moveToNext()) {
            String mId = cursor.getString(id);
            String mName = cursor.getString(name);
//            String mCount = cursor.getInt(count)+"";
            String phNumber = cursor.getString(number);
            String callType = cursor.getString(type);
            String callDate = cursor.getString(date);
            Date callDayTime = new Date(Long.valueOf(callDate));
            String callDuration = cursor.getString(duration);
//            String mPhotoUri = cursor.getString(photoUri);
            String mIsRead = cursor.getString(isRead);
            String dir = null;
            int dircode = Integer.parseInt(callType);
            switch (dircode) {
                case CallLog.Calls.OUTGOING_TYPE:
                    dir = "OUTGOING";
                    break;
                case CallLog.Calls.INCOMING_TYPE:
                    dir = "INCOMING";
                    break;

                case CallLog.Calls.MISSED_TYPE:
                    dir = "MISSED";
                    break;
            }
            callLogBeanList.add(new CallLogBean(mId,
                    mName, "", phNumber,
                    callType, callDate, callDayTime.toString(),callDuration,"",mIsRead,dir));// Finally add
//            stringBuffer.append("\nPhone Number:--- " + phNumber + " \nCall Type:--- "
//                    + dir + " \nCall Date:--- " + callDayTime
//                    + " \nCall duration in sec :--- " + callDuration);
            stringBuffer.append("\n----------------------------------");
        }
        cursor.close();
        return callLogBeanList;
    }

}
