package com.example.deepaksharma.androidcode.global;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class StorageInfoUtils {
    /**
     * get all image
     * @param activity
     * @return
     */
    public static List<File> getAllDeviceImage(Activity activity) {
        Uri uri;
        List<File> listOfAllImages = new ArrayList<>();
        Cursor cursor;
        int column_index_data, column_index_folder_name;
        String PathOfImage = null;
//        uri = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
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
