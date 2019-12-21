package com.example.deepaksharma.androidcode.model;

public class SmsBean {


    /**
     * success : 1
     * message : An email sent to your email with a link, follow it to change password
     */

    private int success;
    private String message;

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}