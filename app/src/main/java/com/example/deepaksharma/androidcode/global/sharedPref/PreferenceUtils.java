package com.example.deepaksharma.androidcode.global.sharedPref;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PreferenceUtils {

    private static final String PREFS_NAME = "local_pref";
    private static final String GLOBAL_PREFS_NAME = "global_pref";
    private static SharedPreferences mLocalPreferences;
    private static SharedPreferences mGlobalPreferences;

    public static void getInstance(Context context) {
        mLocalPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        mGlobalPreferences = context.getSharedPreferences(GLOBAL_PREFS_NAME, Context.MODE_PRIVATE);
    }
//    {START LOCAL PREFERENCE  SAVE}

    /**
     * Get data from mPreferenceUtil with key {key} & of type {obj}
     *
     * @param key          preference key
     * @param defautlValue default key for preference
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T getPreference(String key, T defautlValue) {
        try {
            if (defautlValue instanceof String) {
                return (T) mLocalPreferences.getString(key, (String) defautlValue);
            } else if (defautlValue instanceof Integer) {
                return (T) (Integer) mLocalPreferences.getInt(key, (Integer) defautlValue);
            } else if (defautlValue instanceof Boolean) {
                return (T) (Boolean) mLocalPreferences.getBoolean(key, (Boolean) defautlValue);
            } else if (defautlValue instanceof Float) {
                return (T) (Float) mLocalPreferences.getFloat(key, (Float) defautlValue);
            } else if (defautlValue instanceof Long) {
                return (T) (Long) mLocalPreferences.getLong(key, (Long) defautlValue);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Save data to mPreferenceUtil with key {key} & of type {obj}
     *
     * @param key
     * @param value
     * @param <T>
     * @return
     */
    public static <T> void setPreference(String key, T value) {
        try {
            SharedPreferences.Editor editor = mLocalPreferences.edit();
            if (value instanceof String) {
                editor.putString(key, (String) value);
            } else if (value instanceof Integer) {
                editor.putInt(key, (Integer) value);
            } else if (value instanceof Boolean) {
                editor.putBoolean(key, (Boolean) value);
            } else if (value instanceof Float) {
                editor.putFloat(key, (Float) value);
            } else if (value instanceof Long) {
                editor.putLong(key, (Long) value);
            }
            editor.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * clear key preference when required
     */
    public static void removeKey(String key) {
        if (mLocalPreferences != null)
            mLocalPreferences.edit().remove(key).commit();
    }

    /**
     * clear preference when required
     */
    public static void clearAllPreferences() {
        if (mLocalPreferences != null)
            mLocalPreferences.edit().clear().commit();
    }

    /**
     * Clear all Preference accept keyToBeSaved
     *
     * @param keyToBeSaved
     */
    public static void clearAllPreferences(String[] keyToBeSaved) {
        if (mLocalPreferences != null) {
            Map<String, Object> map = new ConcurrentHashMap<>(mLocalPreferences.getAll());
            for (String stringObjectEntry : map.keySet()) {
                if (!Arrays.asList(keyToBeSaved).contains(stringObjectEntry)) {
                    SharedPreferences.Editor editor = mLocalPreferences.edit();
                    editor.remove(stringObjectEntry).commit();
                }
            }
        }
    }
//    {END LOCAL PREFERENCE  SAVE}

//    {START LOCAL PREFERENCE  SAVE}

    /**
     * Get data from mPreferenceUtil with key {key} & of type {obj}
     *
     * @param key          preference key
     * @param defautlValue default key for preference
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T getGlobalPreference(String key, T defautlValue) {
        try {
            if (defautlValue instanceof String) {
                return (T) mGlobalPreferences.getString(key, (String) defautlValue);
            } else if (defautlValue instanceof Integer) {
                return (T) (Integer) mGlobalPreferences.getInt(key, (Integer) defautlValue);
            } else if (defautlValue instanceof Boolean) {
                return (T) (Boolean) mGlobalPreferences.getBoolean(key, (Boolean) defautlValue);
            } else if (defautlValue instanceof Float) {
                return (T) (Float) mGlobalPreferences.getFloat(key, (Float) defautlValue);
            } else if (defautlValue instanceof Long) {
                return (T) (Long) mGlobalPreferences.getLong(key, (Long) defautlValue);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Save data to mPreferenceUtil with key {key} & of type {obj}
     *
     * @param key
     * @param value
     * @param <T>
     * @return
     */
    public static <T> void setGlobalPreference(String key, T value) {
        try {
            SharedPreferences.Editor editor = mGlobalPreferences.edit();
            if (value instanceof String) {
                editor.putString(key, (String) value);
            } else if (value instanceof Integer) {
                editor.putInt(key, (Integer) value);
            } else if (value instanceof Boolean) {
                editor.putBoolean(key, (Boolean) value);
            } else if (value instanceof Float) {
                editor.putFloat(key, (Float) value);
            } else if (value instanceof Long) {
                editor.putLong(key, (Long) value);
            }
            editor.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * clear key preference when required
     */
    public static void removeGlobalKey(String key) {
        if (mGlobalPreferences != null)
            mGlobalPreferences.edit().remove(key).commit();
    }

    /**
     * clear preference when required
     */
    public static void clearAllGlobalPreferences() {
        if (mGlobalPreferences != null)
            mGlobalPreferences.edit().clear().commit();
    }

    /**
     * Clear all Preference accept keyToBeSaved
     *
     * @param keyToBeSaved
     */
    public static void clearAllGlobalPreferences(String[] keyToBeSaved) {
        if (mGlobalPreferences != null) {
            Map<String, Object> map = new ConcurrentHashMap<>(mGlobalPreferences.getAll());
            for (String stringObjectEntry : map.keySet()) {
                if (!Arrays.asList(keyToBeSaved).contains(stringObjectEntry)) {
                    SharedPreferences.Editor editor = mGlobalPreferences.edit();
                    editor.remove(stringObjectEntry).commit();
                }
            }
        }
    }
//    {END GLOBAL PREFERENCE  SAVE}

}
