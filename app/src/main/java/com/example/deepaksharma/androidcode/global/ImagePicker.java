package com.example.deepaksharma.androidcode.global;

import android.app.Activity;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;

import com.example.deepaksharma.androidcode.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ImagePicker {
    private static final String TAG = ImagePicker.class.getSimpleName();
    public static final int REQUEST_CAMERA = 5000;
    public static final int SELECT_FILE = 5001;
    public static final int PICK_IMAGE_MULTIPLE = 5002;
    private static ImagePickerListener mImagePickerListener;
    private static String imageEncoded;
    private static ArrayList<File> selectedImage;
    public static String[] mMimeTypes = {"image/jpeg", "image/png", "image/jpg", "video/mp4"};
    public static String[] mMimeTypesSingle = {"image/jpeg", "image/png", "image/jpg"};

    /**
     * capture image for native camera
     */
    public static void captureRecordImage(Activity activity, ImagePickerListener imagePickerListener) {
        mImagePickerListener = imagePickerListener;
//        new Intent(MediaStore.INTENT_ACTION_STILL_IMAGE_CAMERA);
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        Intent takeVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        takeVideoIntent.putExtra("android.intent.extra.durationLimit", 58000);
        Intent chooserIntent = Intent.createChooser(takePictureIntent, "Capture Image or Video");
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[]{takeVideoIntent});
        activity.startActivityForResult(chooserIntent, REQUEST_CAMERA);
    }

    /**
     * capture image for native camera
     */
    public static void captureImage(Activity activity, ImagePickerListener imagePickerListener) {
        mImagePickerListener = imagePickerListener;
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        activity.startActivityForResult(intent, REQUEST_CAMERA);
    }

    /**
     * pick image from gallery
     */
    public static void galleryImage(Activity activity, ImagePickerListener imagePickerListener) {
        mImagePickerListener = imagePickerListener;
        //Intent intent = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//        Intent intent = new Intent();
////        intent.setDataAndType(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
//        intent.setType("image/*");
//        intent.putExtra(Intent.EXTRA_MIME_TYPES, mMimeTypesSingle);
//        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, false);
//        intent.setAction(Intent.ACTION_GET_CONTENT);
//        //intent.setType("image/*");
//        activity.startActivityForResult(Intent.createChooser(intent, activity.getResources().getString(R.string.select_picture)),
//                PICK_IMAGE_MULTIPLE);

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_MIME_TYPES, mMimeTypesSingle);
        intent.setAction(Intent.ACTION_GET_CONTENT);
        activity.startActivityForResult(Intent.createChooser(intent, activity.getResources().getString(R.string.select_picture)), PICK_IMAGE_MULTIPLE);
    }

    public static void selectMultipleImage(Activity activity, ImagePickerListener imagePickerListener) {
        mImagePickerListener = imagePickerListener;
        Intent intent = new Intent();
        intent.setType("image/* video/*");
        intent.putExtra(Intent.EXTRA_MIME_TYPES, mMimeTypes);
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        intent.setAction(Intent.ACTION_GET_CONTENT);
//        intent.addCategory(Intent.CATEGORY_OPENABLE);
//        intent.setType("*/*");
        activity.startActivityForResult(Intent.createChooser(intent, activity.getResources().getString(R.string.select_picture)), PICK_IMAGE_MULTIPLE);
    }

    //    public static void selectGalleryImage(Activity activity, ImagePickerListener imagePickerListener) {
//        mImagePickerListener = imagePickerListener;
//        Intent intent = new Intent();
//        intent.setType("image/*");
//        intent.putExtra(Intent.EXTRA_MIME_TYPES, mMimeTypesSingle);
////        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
//        intent.setAction(Intent.ACTION_GET_CONTENT);
////        intent.addCategory(Intent.CATEGORY_OPENABLE);
////        intent.setType("*/*");
//        activity.startActivityForResult(Intent.createChooser(intent, activity.getResources().getString(R.string.select_picture)), PICK_IMAGE_MULTIPLE);
//    }
    public static void onActivityResult(Activity activity, int requestCode, int resultCode, Intent data) {
        List<File> file = new ArrayList<>();
        File compressedFiles=null;
        switch (requestCode) {
            case REQUEST_CAMERA:
                Bitmap bitmap = null;
                if (data.getExtras() != null) {
                    bitmap = (Bitmap) data.getExtras().get("data");
                    file.add(FileUtils.saveBitmapImage(bitmap));
                } else if (data.getData() != null) {
                    file = getData(activity, data);
                }
//                 compressedFiles = CompressImage.compressImage(file.toString());
//                Log.d(TAG, "onActivityResult: old Image - " + FileUtils.getFileSizeInMbTest(file.get(0)) +
//                        "\n compress image - " + FileUtils.getFileSizeInMbTest(compressedFiles));
////                file.clear();
//                file.set(0,compressedFiles);
                mImagePickerListener.imagePath(file);
                break;
            case SELECT_FILE:
                Uri uri = data.getData();
                file.add(FileUtils.getPathFromUri(activity, uri));
//                Lg.d(TAG, "onActivityResult: "+LocalImage.getUriRealPath(uri));
                mImagePickerListener.imagePath(file);
                break;
            case PICK_IMAGE_MULTIPLE:
                List<File> files = getData(activity, data);
                for (int i = 0; i < files.size(); i++) {
                    if (FileUtils.getMimeType(files.get(i).toString()).equals(ImagePicker.mMimeTypes[0]) ||
                            FileUtils.getMimeType(files.get(i).toString()).equals(ImagePicker.mMimeTypes[1]) ||
                            FileUtils.getMimeType(files.get(i).toString()).equals(ImagePicker.mMimeTypes[2])) {
                         compressedFiles = CompressImage.compressImage(activity,files.get(i).toString());
                        Log.d(TAG, "onActivityResult: old Image - " + FileUtils.getFileSizeInMbTest(files.get(i)) +
                                "\n compress image - " + FileUtils.getFileSizeInMbTest(compressedFiles));
                        files.set(i, compressedFiles);
                    }
                }
                mImagePickerListener.imagePath(files);
                break;
        }
    }

    private static ArrayList<File> getData(Activity activity, Intent data) {
        selectedImage = new ArrayList<>();
        if (data.getClipData() != null) {
            for (int i = 0; i < data.getClipData().getItemCount(); i++) {
                Uri uri = data.getClipData().getItemAt(i).getUri();
                selectedImage.add(new File(getPath(activity, uri)));
            }
        } else {
            String pathh = getPath(activity, data.getData());
            selectedImage.add(new File(pathh));
        }
        return selectedImage;
    }

//    private static ArrayList<String> getImage(Intent data) {
//        String[] filePathColumn = {MediaStore.Images.Media.DATA};
//        ArrayList<String> imagesEncodedList = new ArrayList<String>();
//        if (data.getData() != null) {
//            Uri mImageUri = data.getData();
//            // Get the cursor
//            Cursor cursor = activity.getContentResolver().query(mImageUri,
//                    filePathColumn, null, null, null);
//            // Move to first row
//            cursor.moveToFirst();
//            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
//            imageEncoded = cursor.getString(columnIndex);
//            cursor.close();
//        } else {
//            if (data.getClipData() != null) {
//                ClipData mClipData = data.getClipData();
//                ArrayList<Uri> mArrayUri = new ArrayList<Uri>();
//                for (int i = 0; i < mClipData.getItemCount(); i++) {
//
//                    ClipData.Item item = mClipData.getItemAt(i);
//                    Uri uri = item.getUri();
//                    mArrayUri.add(uri);
//                    // Get the cursor
//                    Cursor cursor = activity .getContentResolver().query(uri, filePathColumn, null, null, null);
//                    // Move to first row
//                    cursor.moveToFirst();
//
//                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
//                    imageEncoded = cursor.getString(columnIndex);
//                    imagesEncodedList.add(imageEncoded);
//                    cursor.close();
//
//                }
//                Lg.d(TAG, "selected Images" + mArrayUri.size());
//            }
//        }
//        return imagesEncodedList;
//    }

    public interface ImagePickerListener {
        void imagePath(List<File> filePath);
    }

    public static String getPath(final Context context, final Uri uri) {

        final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;

        // DocumentProvider
        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }

                // TODO handle non-primary volumes
            }
            // DownloadsProvider
            else if (isDownloadsDocument(uri)) {

                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));

                return getDataColumn(context, contentUri, null, null);
            }
            // MediaProvider
            else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }

                final String selection = "_id=?";
                final String[] selectionArgs = new String[]{
                        split[1]
                };

                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        }
        // MediaStore (and general)
        else if ("content".equalsIgnoreCase(uri.getScheme())) {
            return getDataColumn(context, uri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }

        return null;
    }

    /**
     * Get the value of the data column for this Uri. This is useful for
     * MediaStore Uris, and other file-based ContentProviders.
     *
     * @param context       The context.
     * @param uri           The Uri to query.
     * @param selection     (Optional) Filter used in the query.
     * @param selectionArgs (Optional) Selection arguments used in the query.
     * @return The value of the _data column, which is typically a file path.
     */
    public static String getDataColumn(Context context, Uri uri, String selection,
                                       String[] selectionArgs) {

        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {
                column
        };

        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                    null);
            if (cursor != null && cursor.moveToFirst()) {
                final int column_index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(column_index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }


    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is ExternalStorageProvider.
     */
    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

}
