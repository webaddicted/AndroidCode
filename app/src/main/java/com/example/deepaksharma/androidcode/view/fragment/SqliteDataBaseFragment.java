package com.example.deepaksharma.androidcode.view.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.databinding.ViewDataBinding;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;

import com.example.deepaksharma.androidcode.R;
import com.example.deepaksharma.androidcode.databinding.FragmentShareBinding;
import com.example.deepaksharma.androidcode.databinding.FragmentSqliteDatabaseBinding;
import com.example.deepaksharma.androidcode.global.FileUtils;
import com.example.deepaksharma.androidcode.global.UserDbHelper;
import com.example.deepaksharma.androidcode.global.image.ImagePicker;
import com.example.deepaksharma.androidcode.utils.GlobalUtilities;
import com.example.deepaksharma.androidcode.view.base.BaseFragment;
import com.example.deepaksharma.androidcode.view.home.HomeActivity;
import com.example.deepaksharma.androidcode.viewModel.home.HomeViewModel;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.List;

public class SqliteDataBaseFragment extends BaseFragment {
    public static final String TAG = SqliteDataBaseFragment.class.getSimpleName();
    private FragmentSqliteDatabaseBinding mBinding;
    private HomeViewModel mHomeViewModel;
    private boolean mIsSqliteBoolean = false;
    private boolean mIsRoomDbBoolean = false;
    private UserDbHelper mUserDbHelper;
    private SQLiteDatabase mSqLiteDatabase;

    public static SqliteDataBaseFragment getInstance(Bundle bundle) {
        SqliteDataBaseFragment fragment = new SqliteDataBaseFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_sqlite_database;
    }

    @Override
    protected void onViewsInitialized(ViewDataBinding binding, View view) {
        mBinding = (FragmentSqliteDatabaseBinding) binding;
        mHomeViewModel = ViewModelProviders.of(getActivity()).get(HomeViewModel.class);
        clickListener();
    }

    private void clickListener() {
        mBinding.btnSqliteDataBase.setOnClickListener(this);
        mBinding.btnRoomDatabase.setOnClickListener(this);
        mBinding.btnInsertUser.setOnClickListener(this);
        mBinding.btnGetAllUser.setOnClickListener(this);
        mBinding.btnUpdateUser.setOnClickListener(this);
        mBinding.btnDeleteUser.setOnClickListener(this);
        mBinding.btnDeleteAllUser.setOnClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        ((HomeActivity) getActivity()).showBackBtn();
        ((HomeActivity) getActivity()).setToolbarTitle(getResources().getString(R.string.share_file_title));
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.btn_sqlite_data_base:
                mIsSqliteBoolean = true;
                mIsRoomDbBoolean = false;
                mUserDbHelper = new UserDbHelper(getActivity());
                mSqLiteDatabase = mUserDbHelper.getWritableDatabase();
                mSqLiteDatabase = mUserDbHelper.getReadableDatabase();
                mBinding.txtDataBaseType.setText(getResources().getString(R.string.sqlite_database));
                break;
            case R.id.btn_room_database:
                mIsSqliteBoolean = false;
                mIsRoomDbBoolean = true;
                mBinding.txtDataBaseType.setText(getResources().getString(R.string.room_database));
                break;
            case R.id.btn_insert_user:
                insert();
                break;
            case R.id.btn_get_all_user:
                getUserInfo();
                break;
            case R.id.btn_update_user:
                updateUser();
                break;
            case R.id.btn_delete_user:
                deleteUser();
                break;
            case R.id.btn_delete_all_user:
                deleteAllUser();
                break;
//            case R.id.btn_delete_table:
//                break;
        }
    }

    private void insert() {
        if (mIsSqliteBoolean) {
            int randomInt = GlobalUtilities.getTwoDigitRandomNo();
            mUserDbHelper.insertUserInfo("Deepak_" + randomInt, "1234" + randomInt, mSqLiteDatabase);
            mBinding.txtInsertUser.setText(getResources().getString(R.string.user_successfully_inserted));
        } else if (mIsRoomDbBoolean) {

        }
    }

    private void getUserInfo() {
        if (mIsSqliteBoolean) {
            StringBuilder stringBuilder = new StringBuilder();
            Cursor cursor = mUserDbHelper.getAllUserInfo(mSqLiteDatabase);
            if (cursor.moveToFirst()) {
                do {
                    String strUsername = cursor.getString(0);
                    String strMobileNo = cursor.getString(1);
                    stringBuilder.append("userName - " + strUsername + "\nmobile no - " + strMobileNo + "\n\n");
                } while (cursor.moveToNext());
            }
            mBinding.txtGetAllUser.setText(stringBuilder.toString());
        } else if (mIsRoomDbBoolean) {

        }
    }

    private void updateUser() {
        if (mIsSqliteBoolean) {
            String userName = mBinding.edtUserName.getText().toString();
            if (userName.length() > 0)
                mUserDbHelper.updateUserInfo(userName, "1234" + GlobalUtilities.getTwoDigitRandomNo(), mSqLiteDatabase);
            mBinding.txtUpdateUser.setText(getResources().getString(R.string.user_successfully_updated));
        } else if (mIsRoomDbBoolean) {

        }
    }

    private void deleteUser() {
        if (mIsSqliteBoolean) {
            String userName = mBinding.edtDeleteUser.getText().toString();
            if (userName.length() > 0)
                mUserDbHelper.deleteUser(userName, mSqLiteDatabase);
            mBinding.txtDeleteUser.setText(getResources().getString(R.string.user_successfully_deleted));
        } else if (mIsRoomDbBoolean) {

        }
    }

    private void deleteAllUser() {
        if (mIsSqliteBoolean) {
            mUserDbHelper.deleteAllData(mSqLiteDatabase);
            mBinding.txtDeleteAllUser.setText(getResources().getString(R.string.table_successfully_clear));
        } else if (mIsRoomDbBoolean) {
        }
    }

}