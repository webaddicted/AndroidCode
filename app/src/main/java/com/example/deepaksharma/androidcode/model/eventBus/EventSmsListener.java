package com.example.deepaksharma.androidcode.model.eventBus;

import com.example.deepaksharma.androidcode.model.SmsBean;

import java.util.List;

public class EventSmsListener {
    List<SmsBean> mAllSms;
    public EventSmsListener(List<SmsBean> allSms) {
    this.mAllSms = allSms;
    }

    public List<SmsBean> getmAllSms() {
        return mAllSms;
    }

    public void setmAllSms(List<SmsBean> mAllSms) {
        this.mAllSms = mAllSms;
    }
}
