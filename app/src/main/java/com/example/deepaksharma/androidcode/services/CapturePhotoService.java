package com.example.deepaksharma.androidcode.services;

import android.app.Service;
import android.content.Intent;
import android.hardware.Camera;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.StrictMode;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CapturePhotoService extends Service {
    private static final String TAG = CapturePhotoService.class.getSimpleName();
    private SurfaceHolder sHolder;
    private Camera mCamera;
    private Camera.Parameters parameters;


    @Override
    public void onCreate() {
        super.onCreate();
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy =
                    new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        ;
        Thread myThread = null;


    }

    @Override
    public void onStart(Intent intent, int startId) {

        super.onStart(intent, startId);

        if (Camera.getNumberOfCameras() >= 2) {
            mCamera = Camera.open(Camera.CameraInfo.CAMERA_FACING_FRONT);
        }

        if (Camera.getNumberOfCameras() < 2) {
            mCamera = Camera.open();
        }
        methodCallFixTime();
    }

    Camera.PictureCallback mCall = new Camera.PictureCallback() {

        public void onPictureTaken(final byte[] data, Camera camera) {
            Log.i(TAG, "folder===>" + data + "\n" + camera);
            FileOutputStream outStream = null;
            try {

                File sd = new File(Environment.getExternalStorageDirectory(), "A");
                if (!sd.exists()) {
                    sd.mkdirs();
                    Log.i(TAG, "folder" + Environment.getExternalStorageDirectory());
                }

                Calendar cal = Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
                String tar = (sdf.format(cal.getTime()));

                outStream = new FileOutputStream(sd + tar + ".jpg");
                outStream.write(data);
                outStream.close();

                Log.i("CAM", data.length + " byte written to:" + sd + tar + ".jpg");
//                camkapa(sHolder);


            } catch (FileNotFoundException e) {
                Log.d(TAG, e.getMessage());
            } catch (IOException e) {
                Log.d(TAG, e.getMessage());
            }
        }
    };


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void methodCallFixTime() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
//                Toast.makeText(CapPhoto.this, "call Method every 1 sec", Toast.LENGTH_SHORT).show();
                SurfaceView sv = new SurfaceView(getApplicationContext());
                try {
                    mCamera.setPreviewDisplay(sv.getHolder());
                    parameters = mCamera.getParameters();
                    mCamera.setParameters(parameters);
                    mCamera.startPreview();

                    mCamera.takePicture(null, null, mCall);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                sHolder = sv.getHolder();
                sHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
            }
        }, 3000);
    }

    public void camkapa(SurfaceHolder sHolder) {
        if (null == mCamera)
            return;
        mCamera.stopPreview();
        mCamera.release();
        mCamera = null;
        Log.i(TAG, " closed");
    }

}
