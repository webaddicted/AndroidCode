package com.example.deepaksharma.androidcode.global.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.deepaksharma.androidcode.global.db.entity.UserInfo;

import java.util.List;

@Dao
public interface UserInfoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertUser(UserInfo userInfo);

    @Query("SELECT * FROM user_info")
    public List<UserInfo> getUserInfo();

    @Update(onConflict = OnConflictStrategy.REPLACE)
    public void updateUserInfo(UserInfo userInfo);

    @Delete
    public void deleteUser(UserInfo userInfo);

    @Query("DELETE FROM user_info")
    public void cleatTable();

//    @Insert
//    void insertGame(GameTemp gameTemp, List<GameBean.ResultBean.DataBean.EnvObjBean> envObjBean,
//                    List<GameBean.ResultBean.DataBean.GameObjBean> gameObjBean,
//                    List<GameBean.ResultBean.DataBean.ZoneObjBean> zoneObjBean);
//
//
////    @Query("SELECT * FROM GameTemp")
//    @Query("SELECT * FROM GameTemp gt inner join EnvObjBean et on gt.id_temmmp = et.id_parent inner join ZoneObjBean zt on et.id_parent = zt.id_parent")
//    public List<GameInsert> getGameInfo();
//
////paging START
//    @Insert//(onConflict = OnConflictStrategy.REPLACE)
//    public void insertArtist(List<Pagination.ArticlesBean> articlesBeans);
//
//    //to fetch data required to display in each page
//    @Query("SELECT * FROM ArticlesBean WHERE  id >= :id LIMIT :size")
//    public List<Pagination.ArticlesBean> getCouponsBySize(int id, int size);
//
//    @Query("SELECT * FROM ArticlesBean")
//    public DataSource.Factory<Integer, Pagination.ArticlesBean> newsssss();

//    @Query("DELETE FROM ArticlesBean")
//    public void clearSData();
}
