package com.example.deepaksharma.androidcode.model.common;

public class CallLogBean {

    // Getter and setter for contacts
    private String mId,
            mName, mCount, phNumber,
            callType, callDate, callDayTime, callDuration, mPhotoUri, mIsRead, dir;

    public CallLogBean(String mId, String mName, String mCount, String phNumber, String callType, String callDate, String callDayTime, String callDuration, String mPhotoUri, String mIsRead, String dir) {
        this.mId = mId;
        this.mName = mName;
        this.mCount = mCount;
        this.phNumber = phNumber;
        this.callType = callType;
        this.callDate = callDate;
        this.callDayTime = callDayTime;
        this.callDuration = callDuration;
        this.mPhotoUri = mPhotoUri;
        this.mIsRead = mIsRead;
        this.dir = dir;
    }

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmCount() {
        return mCount;
    }

    public void setmCount(String mCount) {
        this.mCount = mCount;
    }

    public String getPhNumber() {
        return phNumber;
    }

    public void setPhNumber(String phNumber) {
        this.phNumber = phNumber;
    }

    public String getCallType() {
        return callType;
    }

    public void setCallType(String callType) {
        this.callType = callType;
    }

    public String getCallDate() {
        return callDate;
    }

    public void setCallDate(String callDate) {
        this.callDate = callDate;
    }

    public String getCallDayTime() {
        return callDayTime;
    }

    public void setCallDayTime(String callDayTime) {
        this.callDayTime = callDayTime;
    }

    public String getCallDuration() {
        return callDuration;
    }

    public void setCallDuration(String callDuration) {
        this.callDuration = callDuration;
    }

    public String getmPhotoUri() {
        return mPhotoUri;
    }

    public void setmPhotoUri(String mPhotoUri) {
        this.mPhotoUri = mPhotoUri;
    }

    public String getmIsRead() {
        return mIsRead;
    }

    public void setmIsRead(String mIsRead) {
        this.mIsRead = mIsRead;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }
}