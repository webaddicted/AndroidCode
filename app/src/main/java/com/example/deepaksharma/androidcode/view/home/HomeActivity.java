package com.example.deepaksharma.androidcode.view.home;

import android.content.ClipData;
import android.content.Intent;
import android.database.Cursor;
import android.databinding.ViewDataBinding;
import android.location.Location;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;

import com.example.deepaksharma.androidcode.R;
import com.example.deepaksharma.androidcode.databinding.ActivityHomeBinding;
import com.example.deepaksharma.androidcode.global.constant.AppConstant;
import com.example.deepaksharma.androidcode.utils.GlobalUtilities;
import com.example.deepaksharma.androidcode.utils.Lg;
import com.example.deepaksharma.androidcode.view.base.BaseActivity;
import com.example.deepaksharma.androidcode.view.base.BaseLocation;
import com.example.deepaksharma.androidcode.view.fragment.TaskListFragment;

import java.util.ArrayList;

public class HomeActivity extends BaseLocation implements View.OnClickListener {
    private static final String TAG = HomeActivity.class.getSimpleName();
    private ActivityHomeBinding mBinding;
    private ArrayList<String> imagesEncodedList;
    private String imageEncoded;

    @Override
    public int getLayout() {
        return R.layout.activity_home;
    }

    @Override
    public void initUI(ViewDataBinding binding) {
        mBinding = (ActivityHomeBinding) binding;
        init();
        navigateScreen(TaskListFragment.TAG);
    }

    private void init() {
        mBinding.include.imgBack.setOnClickListener(this);
    }

    public void setToolbarTitle(String title) {
        mBinding.include.txtTitle.setText(title);
    }

    public void showBackBtn() {
        mBinding.include.imgBack.setVisibility(View.VISIBLE);
    }

    public void hideBackBtn() {
        mBinding.include.imgBack.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                onBackPressed();
                break;
        }
    }

    /**
     * navigate on fragment
     *
     * @param tag represent navigation activity
     */
    private void navigateScreen(String tag) {
        Fragment frm;
        if (tag.equals(TaskListFragment.TAG)) {
            frm = TaskListFragment.getInstance(getIntent().getExtras());
            navigateFragment(R.id.container, frm, false);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            // When an Image is picked
            if (requestCode == AppConstant.PICK_IMAGE_MULTIPLE && resultCode == RESULT_OK
                    && data != null) {
                // Get the Image from data
                String[] filePathColumn = {MediaStore.Images.Media.DATA};
                imagesEncodedList = new ArrayList<String>();
                if (data.getData() != null) {
                    Uri mImageUri = data.getData();
                    // Get the cursor
                    Cursor cursor = getContentResolver().query(mImageUri,
                            filePathColumn, null, null, null);
                    // Move to first row
                    cursor.moveToFirst();
                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    imageEncoded = cursor.getString(columnIndex);
                    cursor.close();
                } else {
                    if (data.getClipData() != null) {
                        ClipData mClipData = data.getClipData();
                        ArrayList<Uri> mArrayUri = new ArrayList<Uri>();
                        for (int i = 0; i < mClipData.getItemCount(); i++) {

                            ClipData.Item item = mClipData.getItemAt(i);
                            Uri uri = item.getUri();
                            mArrayUri.add(uri);
                            // Get the cursor
                            Cursor cursor = getContentResolver().query(uri, filePathColumn, null, null, null);
                            // Move to first row
                            cursor.moveToFirst();

                            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                            imageEncoded = cursor.getString(columnIndex);
                            imagesEncodedList.add(imageEncoded);
                            cursor.close();

                        }
                        Lg.d(TAG, "selected Images" + mArrayUri.size());
                    }
                }
            } else {
                GlobalUtilities.showToast("You haven't picked Image");
            }
        } catch (Exception e) {
            GlobalUtilities.showToast("Something went wrong");
        }
    }

    @Override
    public void getCurrentLocation(@NonNull Location location, @NonNull String address) {
        Log.d(TAG, "getCurrentLocation: getLatitude ->  " + location.getLatitude() + "   getLongitude  ->   " + location.getLongitude());
    if (address!=null){
        Log.d(TAG, "getCurrentLocation: getLatitude ->  " + location.getLatitude() + "   getLongitude  ->   " + location.getLongitude()+"  address  ->  "+address);
    }
    }
}

