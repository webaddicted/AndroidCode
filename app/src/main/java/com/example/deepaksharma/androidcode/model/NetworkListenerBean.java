package com.example.deepaksharma.androidcode.model;

public class NetworkListenerBean {
    private boolean isConnected;

    public NetworkListenerBean(boolean isConnected) {
        this.isConnected  = isConnected;
    }

    public boolean isConnected() {
        return isConnected;
    }

    public void setConnected(boolean connected) {
        isConnected = connected;
    }
}
