package com.example.deepaksharma.androidcode.global.image;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.util.Log;

import androidx.annotation.IntDef;

import com.example.deepaksharma.androidcode.R;
import com.example.deepaksharma.androidcode.global.CompressImage;
import com.example.deepaksharma.androidcode.global.FileUtils;
import com.example.deepaksharma.androidcode.global.PermissionsHandler;

import java.io.File;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

import static com.example.deepaksharma.androidcode.global.image.MediaPickerHelper.getData;

public class MediaPickerUtils {
    private static final String TAG = MediaPickerUtils.class.getSimpleName();

    public static final int CAPTURE_IMAGE = 600;
    public static final int SELECT_IMAGE = 601;
    public static final int SELECT_VIDEO = 602;
    public static final int RECORD_VIDEO = 603;
    public static final int SELECT_MULTIPLE_IMAGE = 604;
    public static final int CAPTURE_RECORD_VIDEO = 605;

    public static final int REQUEST_CAMERA_VIDEO = 5000;
    public static final int REQUEST_SELECT_FILE_FROM_GALLERY = 5002;
    private static ImagePickerListener mImagePickerListener;
    public static String[] mMimeTypes = {"image/jpeg", "image/png", "image/jpg", "video/mp4"};
    public static String[] mVideoMimeTypes = {"video/3gp", "video/mpeg", "video/avi", "video/mp4"};
    public static String[] mImageMimeTypes = {"image/jpeg", "image/png", "image/jpg"};
    private static File mfilePath;

    @IntDef({CAPTURE_IMAGE, SELECT_IMAGE, SELECT_VIDEO, RECORD_VIDEO, SELECT_MULTIPLE_IMAGE, CAPTURE_RECORD_VIDEO})
    @Retention(RetentionPolicy.SOURCE)
    public @interface FilePickerType {
    }

    /**
     * select media option
     *
     * @param activity
     * @param fileType
     * @param imagePickerListener
     */
    public static void selectMediaOption(Activity activity, @FilePickerType int fileType, File filePath, ImagePickerListener imagePickerListener) {
        mImagePickerListener = imagePickerListener;
        mfilePath = filePath;
        checkPermission(activity, fileType);
    }

    /**
     * check file storage permission
     *
     * @param activity
     * @param fileType
     */
    private static void checkPermission(Activity activity, @FilePickerType int fileType) {
        List<String> locationList = new ArrayList<>();
        locationList.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        locationList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        locationList.add(Manifest.permission.CAMERA);
        if (PermissionsHandler.requestMultiplePermission(activity, locationList, new PermissionsHandler.PermissionListener() {
            @Override
            public void onPermissionGranted(List<String> mCustomPermission) {
                FileUtils.createApplicationFolder();
                selectMediaType(activity, fileType);
            }

            @Override
            public void onPermissionDenied(List<String> mCustomPermission) {

            }
        })) {
            FileUtils.createApplicationFolder();
            selectMediaType(activity, fileType);
        }
    }

    private static void selectMediaType(Activity activity, @FilePickerType int fileType) {
        Intent intent = null;
        switch (fileType) {
//            capture image for native camera
            case CAPTURE_IMAGE:
                intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                activity.startActivityForResult(intent, REQUEST_CAMERA_VIDEO);
                break;
//            pick image from gallery
            case SELECT_IMAGE:
                intent = new Intent();
                intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_MIME_TYPES, mImageMimeTypes);
                intent.setAction(Intent.ACTION_GET_CONTENT);
                activity.startActivityForResult(Intent.createChooser(intent, activity.getResources().getString(R.string.select_picture)), REQUEST_SELECT_FILE_FROM_GALLERY);
                break;
//                select video from gallery
            case SELECT_VIDEO:
                intent = new Intent();
                intent.setType("video/*");
                intent.putExtra(Intent.EXTRA_MIME_TYPES, mVideoMimeTypes);
                intent.setAction(Intent.ACTION_GET_CONTENT);
                activity.startActivityForResult(Intent.createChooser(intent, activity.getResources().getString(R.string.select_video)), REQUEST_SELECT_FILE_FROM_GALLERY);
                break;
//                record video
            case RECORD_VIDEO:
                intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_DURATION_LIMIT, 5);
                intent.putExtra("EXTRA_VIDEO_QUALITY", 0);
                activity.startActivityForResult(intent, REQUEST_CAMERA_VIDEO);
                break;
//                select multiple image
            case SELECT_MULTIPLE_IMAGE:
                intent = new Intent();
                intent.setType("image/* video/*");
                intent.putExtra(Intent.EXTRA_MIME_TYPES, mMimeTypes);
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                intent.setAction(Intent.ACTION_GET_CONTENT);
                activity.startActivityForResult(Intent.createChooser(intent, activity.getResources().getString(R.string.select_picture)), REQUEST_SELECT_FILE_FROM_GALLERY);
                break;
//            capture image & record video
            case CAPTURE_RECORD_VIDEO:
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                Intent takeVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                takeVideoIntent.putExtra(MediaStore.EXTRA_DURATION_LIMIT, 5);
                takeVideoIntent.putExtra("EXTRA_VIDEO_QUALITY", 0);
                intent = Intent.createChooser(takePictureIntent, "Capture Image or Video");
                intent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[]{takeVideoIntent});
                activity.startActivityForResult(intent, REQUEST_CAMERA_VIDEO);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + fileType);
        }
    }

    public static void onActivityResult(Activity activity, int requestCode, int resultCode, Intent data) {
        List<File> file = new ArrayList<>();
        File compressedFiles = null;
        switch (requestCode) {
            case REQUEST_CAMERA_VIDEO:
                Bitmap bitmap = null;
                if (data.getExtras() != null) {
                    bitmap = (Bitmap) data.getExtras().get("data");
                    File originalFile = FileUtils.saveImage(bitmap, mfilePath);
                    file.add(originalFile);
//                    Log.d(TAG, "onActivityResult: old Image - " + FileUtils.getFileSizeInMbTest(originalFile) +
//                            "\n compress image - " + FileUtils.getFileSizeInMbTest(compressedFiles));
                } else if (data.getData() != null) {
                    // in case of record video
                    file = getData(activity, data);
                }
                mImagePickerListener.imagePath(file);
                break;
//            case SELECT_FILE:
//                Uri uri = data.getData();
//                file.add(FileUtils.getPathFromUri(activity, uri));
//                mImagePickerListener.imagePath(file);
//                break;
            case REQUEST_SELECT_FILE_FROM_GALLERY:
                List<File> files = getData(activity, data);
                for (int i = 0; i < files.size(); i++) {
                    String filePath = files.get(i).toString();
                    filePath.substring(filePath.lastIndexOf(".") + 1);
                    if (filePath.contains(MediaPickerUtils.mMimeTypes[0]) ||
                            filePath.contains(MediaPickerUtils.mMimeTypes[1]) ||
                            filePath.contains(MediaPickerUtils.mMimeTypes[2])) {
                        compressedFiles = CompressImage.compressImage(activity, files.get(i).toString());
                        Log.d(TAG, "onActivityResult: old Image - " + FileUtils.getFileSizeInMbTest(files.get(i)) +
                                "\n compress image - " + FileUtils.getFileSizeInMbTest(compressedFiles));
                        files.set(i, compressedFiles);
                    }
                }
                mImagePickerListener.imagePath(files);
                break;
        }
    }

    public interface ImagePickerListener {
        void imagePath(List<File> filePath);
    }
}
