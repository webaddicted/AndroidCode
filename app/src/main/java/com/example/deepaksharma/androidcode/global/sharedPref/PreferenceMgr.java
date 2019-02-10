package com.example.deepaksharma.androidcode.global.sharedPref;

import com.example.deepaksharma.androidcode.global.constant.PreferenceConstant;
import com.example.deepaksharma.androidcode.model.preference.PreferenceBean;

public class PreferenceMgr {
    public static void setUserInfo(PreferenceBean preferenceBean) {
        PreferenceUtils.setPreference(PreferenceConstant.PREF_USER_NAME, preferenceBean.getName());
        PreferenceUtils.setPreference(PreferenceConstant.PREF_USER_AGE, preferenceBean.getAge());
        PreferenceUtils.setPreference(PreferenceConstant.PREF_USER_GENDER, preferenceBean.getGender());
        PreferenceUtils.setPreference(PreferenceConstant.PREF_USER_WEIGHT, preferenceBean.getWeight());
        PreferenceUtils.setPreference(PreferenceConstant.PREF_USER_IS_MARRIED, preferenceBean.isMarried());
    }

    public static PreferenceBean getUserInfo() {
        PreferenceBean preferenceBean = new PreferenceBean();
        preferenceBean.setName(PreferenceUtils.getPreference(PreferenceConstant.PREF_USER_NAME, ""));
        preferenceBean.setAge(PreferenceUtils.getPreference(PreferenceConstant.PREF_USER_AGE, 0));
        preferenceBean.setGender(PreferenceUtils.getPreference(PreferenceConstant.PREF_USER_GENDER, ""));
        preferenceBean.setWeight(PreferenceUtils.getPreference(PreferenceConstant.PREF_USER_WEIGHT, 0L));
        preferenceBean.setMarried(PreferenceUtils.getPreference(PreferenceConstant.PREF_USER_IS_MARRIED, false));
        return preferenceBean;
    }

    /**
     * remove key from local preference
     */
    public static void removeKey(String key) {
        if (key != null) {
            PreferenceUtils.removeKey(key);
        }
    }

    /**
     * remove key from global preference
     */
    public static void removeGlobalKey(String key) {
        if (key != null) {
            PreferenceUtils.removeGlobalKey(key);
        }
    }

    /**
     * clear local preference
     */
    public static void clearPreference() {
        PreferenceUtils.clearAllPreferences();
    }

    /**
     * clear global preference
     */
    public static void clearGlobalPreference() {
        PreferenceUtils.clearAllGlobalPreferences();
    }
}
