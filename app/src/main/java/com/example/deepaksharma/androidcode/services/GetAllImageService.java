package com.example.deepaksharma.androidcode.services;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import com.example.deepaksharma.androidcode.global.AppApplication;
import com.example.deepaksharma.androidcode.model.NetworkListenerBean;
import com.example.deepaksharma.androidcode.model.eventBus.EventAllImageListener;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GetAllImageService extends IntentService {
    public static final String TAG = GetAllImageService.class.getSimpleName();

    public GetAllImageService() {
        super("GetAllImageService");
    }
    @Override
    protected void onHandleIntent(Intent intent) {
        EventBus.getDefault().post(new EventAllImageListener(getAllDeviceImage(AppApplication.getInstance())));
    }
    public static List<File> getAllDeviceImage(Context activity) {
        Uri uri;
        List<File> listOfAllImages = new ArrayList<>();
        Cursor cursor;
        int column_index_data, column_index_folder_name;
        String PathOfImage = null;
        uri = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        String[] projection = {MediaStore.MediaColumns.DATA,
                MediaStore.Images.Media.BUCKET_DISPLAY_NAME};

        cursor = activity.getContentResolver().query(uri, projection, null,
                null, null);

        column_index_data = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        column_index_folder_name = cursor
                .getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME);
        while (cursor.moveToNext()) {
            PathOfImage = cursor.getString(column_index_data);
            listOfAllImages.add(new File(PathOfImage));
        }
        return listOfAllImages;
    }
}
