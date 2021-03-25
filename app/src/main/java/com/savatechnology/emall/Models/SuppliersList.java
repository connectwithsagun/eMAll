package com.savatechnology.emall.Models;


public class SuppliersList {

    private int imgSupplier;
    private String tvSupplierName;
    private String tvSupplierAddress;




    public void setimgSupplier(int imgSupplier) {
        this.imgSupplier = imgSupplier;
    }

    public void settvSupplierName(String tvSupplierName) {
        this.tvSupplierName = tvSupplierName;
    }

    public void settvSupplierAddress(String tvSupplierAddress) {
        this.tvSupplierAddress = tvSupplierAddress;
    }






    public SuppliersList(int imgSupplier, String tvSupplierName, String tvSupplierAddress){
        this.imgSupplier=imgSupplier;
        this.tvSupplierName=tvSupplierName;
        this.tvSupplierAddress=tvSupplierAddress;

    }

    public SuppliersList(){

    }


    public int getimgSupplier() {
        return imgSupplier;
    }

    public String gettvSupplierName() {
        return tvSupplierName;
    }

    public String gettvSupplierAddress() {
        return tvSupplierAddress;
    }








}