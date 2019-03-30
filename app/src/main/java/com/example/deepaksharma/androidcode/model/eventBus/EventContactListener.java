package com.example.deepaksharma.androidcode.model.eventBus;

import com.example.deepaksharma.androidcode.model.SmsBean;
import com.example.deepaksharma.androidcode.model.common.ContactsBean;

import java.util.List;

public class EventContactListener {
    List<ContactsBean> mAllContact;
    public EventContactListener(List<ContactsBean> allContact) {
    this.mAllContact = allContact;
    }

    public List<ContactsBean> getmAllContact() {
        return mAllContact;
    }

    public void setmAllContact(List<ContactsBean> mAllContact) {
        this.mAllContact = mAllContact;
    }
}
