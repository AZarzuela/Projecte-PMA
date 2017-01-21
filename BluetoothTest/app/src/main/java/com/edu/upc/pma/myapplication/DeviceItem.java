package com.edu.upc.pma.myapplication;

public class DeviceItem {
    private String deviceName;
    private String address;
    private int connected;

    public String getDeviceName() {
        return deviceName;
    }

    public int getConnected() {
        return connected;
    }

    public String getAddress() {
        return address;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public DeviceItem(String name, String address, int connected){
        this.deviceName = name;
        this.address = address;
        this.connected = connected;
    }

    @Override
    public String toString() {
        return deviceName + "\n" + getAddress();
    }
}

