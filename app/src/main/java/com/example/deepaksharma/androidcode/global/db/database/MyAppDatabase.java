package com.example.deepaksharma.androidcode.global.db.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.deepaksharma.androidcode.global.constant.DbConstant;
import com.example.deepaksharma.androidcode.global.db.dao.MyDao;
import com.example.deepaksharma.androidcode.global.db.entity.Pagination;
import com.example.deepaksharma.androidcode.global.db.entity.UserDetailsObj;
import com.example.deepaksharma.androidcode.global.db.entity.UserInfo;


@Database(entities = {UserInfo.class, UserDetailsObj.class,
        Pagination.ArticlesBean.class}, version = DbConstant.DB_VERSION)
public abstract class MyAppDatabase extends RoomDatabase {
    public abstract MyDao myDao();
}
