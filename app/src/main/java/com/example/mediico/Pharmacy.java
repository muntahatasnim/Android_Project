package com.example.mediico;

public class Pharmacy {
    String pId;
    String userName;
    String Address;
    int phoneNo;

    public Pharmacy() {
    }

    public Pharmacy(String pId, String userName, String address, int phoneNo) {
        this.pId = pId;
        this.userName = userName;
        Address = address;
        this.phoneNo = phoneNo;
    }

    public String getpId() {
        return pId;
    }

    public String getUserName() {
        return userName;
    }

    public String getAddress() {
        return Address;
    }

    public int getPhoneNo() {
        return phoneNo;
    }
}
