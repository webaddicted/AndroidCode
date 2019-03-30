package com.example.deepaksharma.androidcode.global.db.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.deepaksharma.androidcode.global.constant.DbConstant;
import com.example.deepaksharma.androidcode.global.db.dao.UserInfoDao;
import com.example.deepaksharma.androidcode.global.db.entity.UserInfo;

@Database(entities = {UserInfo.class}, version = DbConstant.DB_VERSION)
public abstract class MyAppDatabase extends RoomDatabase {

    public abstract UserInfoDao userInfoDao();
}
