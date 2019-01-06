package com.example.deepaksharma.androidcode.global.db.entity;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.example.deepaksharma.androidcode.global.constant.DbConstant;


@Entity(tableName = DbConstant.USER_DETAILS_TABLE)
public class UserDetailsObj {
     /**
         * id : 711
         * sys : {"country":"GB","sunrise":1381107633,"sunset":1381149604}
         * weather : [{"w_id":711,"main":"Smoke","description":"smoke","icon":"50n"}]
         * main : {"temp":304.15,"pressure":1009}
         */
        @NonNull
        @PrimaryKey(autoGenerate = true)
        private int id;
        @Embedded
        private SysBean sys;
        @Embedded
        private MainBean main;


        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public SysBean getSys() {
            return sys;
        }

        public void setSys(SysBean sys) {
            this.sys = sys;
        }

        public MainBean getMain() {
            return main;
        }

        public void setMain(MainBean main) {
            this.main = main;
        }


        public static class SysBean {
            /**
             * country : GB
             * sunrise : 1381107633
             * sunset : 1381149604
             */

            private String country;
            private int sunrise;
            private int sunset;

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
        public static class WeatherBean {
            /**
             * w_id : 711
             * main : Smoke
             * description : smoke
             * icon : 50n
             */
            @NonNull
            @PrimaryKey(autoGenerate = false)
            private int w_id;
            private String main;
            private String description;
            private String icon;

            public int getW_id() {
                return w_id;
            }

            public void setW_id(int w_id) {
                this.w_id = w_id;
            }

            public String getMain() {
                return main;
            }

            public void setMain(String main) {
                this.main = main;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }
        }

}
