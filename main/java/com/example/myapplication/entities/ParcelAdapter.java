package com.example.myapplication.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

//a class that convert all the argoment to String for enter a parcel to the FireBase and the room

@Entity(tableName = "parcles")
public class ParcelAdapter {
    @PrimaryKey
    @NonNull
    private String PackageId;
    @ColumnInfo(name = "packageType")
    private String packageType;
    @ColumnInfo(name = "fragile")
    private String fragile;
    @ColumnInfo(name = "packageWaight")
    private String packageWaight;
    @ColumnInfo(name = "packageLocation")
    private String packageLocation;
    @ColumnInfo(name = "deliveryDate")
    private String deliveryDate;
    @ColumnInfo(name = "email")
    private String email;
    @ColumnInfo(name = "status")
    private String status;
    @ColumnInfo(name = "ownerName")
    private String ownerName;
    @ColumnInfo(name = "ownerAddress")
    private String ownerAddress;
    @ColumnInfo(name = "ownerPhoneNum")
    private String ownerPhoneNum;
    @ColumnInfo(name = "deliverName")
    private String deliverName;

    @Override
    public String toString() {
        return "ParcelAdapter{" +
                "PackageId='" + PackageId + '\'' +
                ", packageType='" + packageType + '\'' +
                ", fragile='" + fragile + '\'' +
                ", packageWaight='" + packageWaight + '\'' +
                ", packageLocation='" + packageLocation + '\'' +
                ", deliveryDate='" + deliveryDate + '\'' +
                ", email='" + email + '\'' +
                ", status='" + status + '\'' +
                ", ownerName='" + ownerName + '\'' +
                ", ownerAddress='" + ownerAddress + '\'' +
                ", ownerPhoneNum='" + ownerPhoneNum + '\'' +
                ", deliverName='" + deliverName + '\'' +
                '}';
    }

    public ParcelAdapter(Parcel parcel) {
        setPackageId(parcel.getPackageId());
        setPackageType(parcel.getPackageType().toString());
        setFragile("" + parcel.getFragile());
        setPackageWaight(parcel.getPackageWaight().toString());
        setPackageLocation("");
        setDeliveryDate(parcel.getDeliveryDate().toString());
        setEmail(parcel.getEmail());
        setStatus(parcel.getStatus().toString());
        setOwnerName(parcel.getOwnerName());
        setOwnerAddress(parcel.getOwnerAddress());
        setOwnerPhoneNum(parcel.getOwnerPhoneNum());
        setDeliverName(parcel.getDeliverName());
    }

    public ParcelAdapter() {
    }

    //    public Parcel getParcel()
//    {
//       // Parcel parcel = new Parcel();
//        //TO DO
//        return null;
//    }
    public String getPackageId() {
        return PackageId;
    }

    public void setPackageId(String packageId) {
        PackageId = packageId;
    }

    public String getPackageType() {
        return packageType;
    }

    public void setPackageType(String packageType) {
        this.packageType = packageType;
    }

    public String getFragile() {
        return fragile.toString();
    }

    public void setFragile(String fragile) {
        this.fragile = fragile;
    }

    public String getPackageWaight() {
        return packageWaight;
    }

    public void setPackageWaight(String packageWaight) {
        this.packageWaight = packageWaight;
    }

    public String getPackageLocation() {
        return packageLocation;
    }

    public void setPackageLocation(String packageLocation) {
        this.packageLocation = packageLocation;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getDeliverName() {
        return deliverName;
    }

    public void setDeliverName(String deliverName) {
        this.deliverName = deliverName;
    }
}
