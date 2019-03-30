package com.example.deepaksharma.androidcode.model.eventBus;

import com.example.deepaksharma.androidcode.model.common.CallLogBean;
import com.example.deepaksharma.androidcode.model.common.ContactsBean;

import java.util.List;

public class EventCallLogListener {
    List<CallLogBean> mCallLog;
    public EventCallLogListener(List<CallLogBean> callLogBeans) {
    this.mCallLog = callLogBeans;
    }


    public List<CallLogBean> getmCallLog() {
        return mCallLog;
    }

    public void setmCallLog(List<CallLogBean> mCallLog) {
        this.mCallLog = mCallLog;
    }
}
