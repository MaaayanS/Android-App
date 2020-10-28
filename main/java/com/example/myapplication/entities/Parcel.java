package com.example.myapplication.entities;

import android.location.Location;

import com.google.firebase.database.Exclude;

/*
this class have the details of the parcels
 */
public class Parcel {
    private String PackageId = "";
    private Type packageType;
    private boolean fragile;
    private Waight packageWaight;
    private String ownerAddress;
    private dateTime deliveryDate;
    private String email;
    private StatusPackege status = StatusPackege.Sent;
    private String ownerName;
    private String ownerPhoneNum;
    private String deliverName = "NO";

    /*
    toString func-print all the details obout the package
     */
    @Override
    public String toString() {
        return "Parcel{" + "PackageId=" + PackageId +
                "packageType=" + packageType +
                ", fragile=" + fragile +
                ", packageWaight=" + packageWaight +
                ", deliveryDate=" + deliveryDate +
                ", email='" + email + '\'' +
                ", status=" + status +
                ", ownerName='" + ownerName + '\'' +
                ", ownerAddress='" + ownerAddress + '\'' +
                ", ownerPhoneNum='" + ownerPhoneNum + '\'' +
                ", deliverName='" + deliverName + '\'' +
                '}';
    }

    /*
    defult constructor
     */
    public Parcel() {
        this.PackageId = "";
        this.packageType = Type.LARGE_PACKAGE;
        this.fragile = false;
        this.packageWaight = Waight.UP_TO_1_P;
        this.deliveryDate = new dateTime(2020, 12, 01, 00, 00); //defult hour and minute
        this.email = "a@gmail";
        this.status = StatusPackege.On_the_way;
        this.ownerName = "Noa";
        this.ownerAddress = "A";
        this.ownerPhoneNum = "05243";
        this.deliverName = "";
    }

    /*
    constructor
     */
    public Parcel(String packageId, Type packageType, boolean fragile, Waight packageWaight,
                  Location packageLocation, dateTime deliveryDate, String email,
                  StatusPackege status, String ownerName, String ownerAddress,
                  String ownerPhoneNum, String deliverName) {
        this.PackageId = packageId;
        this.packageType = packageType;
        this.fragile = fragile;
        this.packageWaight = packageWaight;
        this.deliveryDate = deliveryDate;
        this.email = email;
        this.status = status;
        this.ownerName = ownerName;
        this.ownerAddress = ownerAddress;
        this.ownerPhoneNum = ownerPhoneNum;
        this.deliverName = deliverName;
    }


    /*
    get&set func
     */
    @Exclude
    public String getPackageId() {
        return PackageId;
    }

    public void setPackageId(String packageId) {
        this.PackageId = packageId;
    }

    public boolean isFragile() {
        return fragile;
    }

    public String getDeliverName() {
        return deliverName;
    }

    public void setDeliverName(String deliverName) {
        this.deliverName = deliverName;
    }


    public dateTime getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(dateTime deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public StatusPackege getStatus() {
        return status;
    }

    public void setStatus(StatusPackege status) {
        this.status = status;
    }


    public Type getPackageType() {
        return packageType;
    }

    public void setPackageType(Type packageType) {
        this.packageType = packageType;
    }

    public boolean getFragile() {
        return fragile;
    }

    public void setFragile(boolean fragile) {
        this.fragile = fragile;
    }

    public Waight getPackageWaight() {
        return packageWaight;
    }

    public void setPackageWaight(Waight packageWaight) {
        this.packageWaight = packageWaight;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerAddress() {
        return ownerAddress;
    }

    public void setOwnerAddress(String ownerAddress) {
        this.ownerAddress = ownerAddress;
    }

    public String getOwnerPhoneNum() {
        return ownerPhoneNum;
    }

    public void setOwnerPhoneNum(String ownerPhoneNum) {
        this.ownerPhoneNum = ownerPhoneNum;
    }


}
