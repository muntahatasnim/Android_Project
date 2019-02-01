package com.example.mediico;

public class Pharmacy {
    String pId;
    String userName;
    String Address;
    String phoneNo;

    public Pharmacy() {
    }

    public Pharmacy(String pId, String userName, String address, String phoneNo) {
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

    public String getPhoneNo() {
        return phoneNo;
    }
}
