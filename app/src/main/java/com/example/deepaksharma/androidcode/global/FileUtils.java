package com.example.deepaksharma.androidcode.global;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;


import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;

public class FileUtils {
    private static final String APP_FOLDER = "criio";
    private static final String SUB_PROFILE = "/profile";
    private static final String SEPARATOR = "/";
    private static final String JPEG = ".jpeg";
    private static final String PNG = ".png";

    public static void createApplicationFolder() {
        File f = new File(String.valueOf(Environment.getExternalStorageDirectory()), File.separator + APP_FOLDER);
        f.mkdirs();
        f = new File(String.valueOf(Environment.getExternalStorageDirectory()), File.separator + APP_FOLDER + SUB_PROFILE);
        f.mkdirs();
    }

    public static File appFolder() {
        return new File(String.valueOf(Environment.getExternalStorageDirectory()), File.separator + APP_FOLDER);
    }

    public static File subFolder() {
        return new File(String.valueOf(Environment.getExternalStorageDirectory()), File.separator + APP_FOLDER + SUB_PROFILE);
    }

    public static String getFileNameFromUrl(URL url) {
        String urlString = url.getFile();
        return urlString.substring(urlString.lastIndexOf('/') + 1).split("\\?")[0].split("#")[0];
    }

//    public static File getPathFromUri(Uri contentUri) {
//        String res = null;
//        String[] proj = {MediaStore.Images.Media.DATA};
//        Cursor cursor = AppInjector.getActivity().getContentResolver().query(contentUri, proj, null, null, null);
//        if (cursor.moveToFirst()) {
//            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
//            res = cursor.getString(column_index);
//        }
//        cursor.close();
//
//        return new File(res);
//    }

    public static File saveBitmapImage(Bitmap bitmap) {
        String filename = System.currentTimeMillis() + JPEG;
        File dest = new File(subFolder(), filename);
        try {
            FileOutputStream out = new FileOutputStream(dest);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dest;
    }

    public static File saveBitmapImg(Bitmap bitmap, String fileName) {

        String filename = String.valueOf(System.currentTimeMillis());
        if (fileName.endsWith(".png"))
            filename = filename + PNG;
        else filename = filename + JPEG;
        File dest = new File(appFolder(), filename);
        try {
            FileOutputStream out = new FileOutputStream(dest);
            if (fileName.endsWith(".png"))
                bitmap.compress(Bitmap.CompressFormat.PNG, 90, out);
            else bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);

            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dest;
    }


}