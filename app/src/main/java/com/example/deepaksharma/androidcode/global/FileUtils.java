package com.example.deepaksharma.androidcode.global;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.webkit.MimeTypeMap;


import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class FileUtils {
    private static final String APP_FOLDER = "AndroidCode";
    private static final String SUB_PROFILE = "/profile";
    private static final String SEPARATOR = "/";
    private static final String JPEG = ".jpeg";
    private static final String PNG = ".png";
    private static final String UPLOAD_IMAGE = "compressed";

    public static void createApplicationFolder() {
        File f = new File(String.valueOf(Environment.getExternalStorageDirectory()), File.separator + APP_FOLDER);
        f.mkdirs();
        f = new File(String.valueOf(Environment.getExternalStorageDirectory()), File.separator + APP_FOLDER + SUB_PROFILE);
        f.mkdirs();
        f = new File(String.valueOf(Environment.getExternalStorageDirectory()), File.separator + APP_FOLDER + SEPARATOR + UPLOAD_IMAGE);
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


    public static File saveBitmapImage(Bitmap bitmap) {
        String filename = System.currentTimeMillis() + JPEG;
        File dest = new File(uploadFile(), filename);
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

    public static File saveImage(Bitmap image) {
        String savedImagePath = null;
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss",
                Locale.getDefault()).format(new Date());
        String imageFileName = "JPEG_" + timeStamp + ".jpg";
        File imageFile = new File(subFolder(), imageFileName);
        savedImagePath = imageFile.getAbsolutePath();
        try {
            OutputStream fOut = new FileOutputStream(imageFile);
            image.compress(Bitmap.CompressFormat.JPEG, 100, fOut);
            fOut.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return imageFile;
    }
    public static File saveImage(Bitmap image, File folderPath) {
        String savedImagePath = null;
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss",
                Locale.getDefault()).format(new Date());
        String imageFileName = "JPEG_" + timeStamp + ".jpg";
        File imageFile = new File(folderPath, imageFileName);
        savedImagePath = imageFile.getAbsolutePath();
        try {
            OutputStream fOut = new FileOutputStream(imageFile);
            image.compress(Bitmap.CompressFormat.JPEG, 100, fOut);
            fOut.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return imageFile;
    }

    public static File uploadFile() {
        return new File(String.valueOf(Environment.getExternalStorageDirectory()), File.separator + APP_FOLDER + File.separator + UPLOAD_IMAGE + "/");
    }

    public static Uri fileIntoUri(File file) {
        return Uri.fromFile(file);
    }

    public static String getMimeType(String url) {
        String type = null;
        String extension = MimeTypeMap.getFileExtensionFromUrl(url);
        if (extension != null) {
            type = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
        }
        if (type == null)
            type = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension.toLowerCase());
        return type;
    }

    public long getFileSizeInKb(File file) {
        return file.length() / 1024;
    }

    public static long getFileSizeInMb(File file) {
        // Get length of file in bytes
        long fileSizeInBytes = file.length();
        // Convert the bytes to Kilobytes (1 KB = 1024 Bytes)
        long fileSizeInKB = fileSizeInBytes / 1024;
        // Convert the KB to MegaBytes (1 MB = 1024 KBytes)
        return fileSizeInKB / 1024;
    }

    public static String getFileSizeInMbTest(File file) {
        // Get length of file in bytes
        long fileSizeInBytes = file.length();
        // Convert the bytes to Kilobytes (1 KB = 1024 Bytes)
        long fileSizeInKB = fileSizeInBytes / 1024;
        // Convert the KB to MegaBytes (1 MB = 1024 KBytes)
        long fileSizeInMB = fileSizeInKB / 1024;
        if (fileSizeInMB > 0) return fileSizeInMB + " MB ";
        else return fileSizeInKB + " KB ";
    }

    public static Bitmap getBitmapFromFile(File image) {
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        return BitmapFactory.decodeFile(image.getAbsolutePath(),bmOptions);
    }
}