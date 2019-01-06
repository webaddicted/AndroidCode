package com.example.deepaksharma.androidcode.global.db.entity;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.List;

public class UserDetailsArray {


    /**
     * id : 711
     * userna : jjgk
     * lastnamee : parik
     * sys : [{"cid":711,"country":"GB","sunrise":1381107633,"sunset":1381149604},{"cid":712,"country":"GBM","sunrise":1381107633,"sunset":1381149604},{"cid":711,"country":"GBB","sunrise":1381107633,"sunset":1381149604}]
     * main : {"temp":304.15,"pressure":1009}
     */
    @NonNull
    @PrimaryKey
    private int id;
    private String userna;
    private String lastnamee;
    @Embedded
    private MainBean main;
    @Ignore
    private List<SysBean> sys;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserna() {
        return userna;
    }

    public void setUserna(String userna) {
        this.userna = userna;
    }

    public String getLastnamee() {
        return lastnamee;
    }

    public void setLastnamee(String lastnamee) {
        this.lastnamee = lastnamee;
    }

    public MainBean getMain() {
        return main;
    }

    public void setMain(MainBean main) {
        this.main = main;
    }

    public List<SysBean> getSys() {
        return sys;
    }

    public void setSys(List<SysBean> sys) {
        this.sys = sys;
    }

    public static class MainBean {
        /**
         * temp : 304.15
         * pressure : 1009
         */

        private double temp;
        private int pressure;

        public double getTemp() {
            return temp;
        }

        public void setTemp(double temp) {
            this.temp = temp;
        }

        public int getPressure() {
            return pressure;
        }

        public void setPressure(int pressure) {
            this.pressure = pressure;
        }
    }

    @Entity
    public static class SysBean {
        /**
         * cid : 711
         * country : GB
         * sunrise : 1381107633
         * sunset : 1381149604
         */
        @PrimaryKey
        private int cid;
        private String country;
        private int sunrise;
        private int sunset;

        public int getCid() {
            return cid;
        }

        public void setCid(int cid) {
            this.cid = cid;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public int getSunrise() {
            return sunrise;
        }

        public void setSunrise(int sunrise) {
            this.sunrise = sunrise;
        }

        public int getSunset() {
            return sunset;
        }

        public void setSunset(int sunset) {
            this.sunset = sunset;
        }
    }
}
