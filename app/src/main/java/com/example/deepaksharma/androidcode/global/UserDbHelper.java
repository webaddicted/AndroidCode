package com.example.deepaksharma.androidcode.global;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.deepaksharma.androidcode.global.constant.DbConstant;

public class UserDbHelper extends SQLiteOpenHelper {
    private static final String TAG = UserDbHelper.class.getSimpleName();
    private Context context;
    private static final int DATABASE_VERSION = DbConstant.DB_VERSION;
    private static final String DATABASE_NAME = DbConstant.DB_NAME;
    private static final String TABLE_NAME = DbConstant.USER_INFO_TABLE;
    private static final String ID = "id";     // Column I (Primary Key)
    private static final String USERNAMEE = "name";    //Column II
    private static final String MOBILENO = "mobileno";  // Column III
    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME +
            " (" + ID + " INTEGER PRIMARY KEY , " + USERNAMEE + " Text ," + MOBILENO + " Text);";

    public UserDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
        Log.d(TAG, "DataBase Created /open........");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
        Log.d(TAG, "TABLE Created........");
    }

    public void insertUserInfo(int id, String strUsername, String strMobileNo, SQLiteDatabase db) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID, id);
        contentValues.put(USERNAMEE, strUsername);
        contentValues.put(MOBILENO, strMobileNo);
        db.insert(TABLE_NAME, null, contentValues);
        Log.d(TAG, "ONE ROW Inserted.........");
    }

    public Cursor getAllUserInfo(SQLiteDatabase db) {
        Cursor cursor;
        String[] projections = {USERNAMEE, MOBILENO};
        cursor = db.query(TABLE_NAME, projections, null, null, null, null, null);
        return cursor;
    }

    public Cursor getUserDetails(String userName, SQLiteDatabase db) {
        String[] projectionss = {USERNAMEE, MOBILENO};
        String selectionn = USERNAMEE + " LIKE ?";
        String[] selection_args = {userName};
        Cursor cursor = db.query(TABLE_NAME, projectionss, selectionn, selection_args, null, null, null);
        return cursor;
    }

    public int updateUserInfo(String userName, String mobileNo, SQLiteDatabase db) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(USERNAMEE, userName);
        contentValues.put(MOBILENO, mobileNo);
        String selection = USERNAMEE + " LIKE ?";
        String[] selection_args = {userName};
        int count = db.update(TABLE_NAME, contentValues, selection, selection_args);
        return count;
    }

    public void deleteUser(String user_name, SQLiteDatabase sqLiteDatabase) {
        String selectionn = USERNAMEE + " LIKE ?";
        String[] selection_arg = {user_name};
        sqLiteDatabase.delete(TABLE_NAME, selectionn, selection_arg);
    }

    public void deleteAllData(SQLiteDatabase db) {
        db.delete(TABLE_NAME, null, null);
    }

    public void deleteTableFromDB(SQLiteDatabase db) {
        db.execSQL("delete from " + DATABASE_NAME);
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
