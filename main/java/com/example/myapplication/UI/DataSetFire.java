package com.example.myapplication.UI;

public class DataSetFire {

    String PackageId;
    String ownerAddress;

    public DataSetFire() {
    }

    public DataSetFire(String teamone, String teamtwo) {
        this.PackageId = teamone;
        this.ownerAddress = teamtwo;
    }

    public String getPackageId() {
        return PackageId;
    }

    public void setPackageId(String teamone) {
        this.PackageId = teamone;
    }

    public String getOwnerAddress() {
        return ownerAddress;
    }

    public void setOwnerAddress(String teamtwo) {
        this.ownerAddress = teamtwo;
    }
}
