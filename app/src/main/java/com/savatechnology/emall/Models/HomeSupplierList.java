package com.savatechnology.emall.Models;


public class HomeSupplierList {

    private String imgSupplier;
    private String tvSupplierName;






    public void setimgSupplier(String imgSupplier) {
        this.imgSupplier = imgSupplier;
    }

    public void settvSupplierName(String tvSupplierName) {
        this.tvSupplierName = tvSupplierName;
    }









    public HomeSupplierList(String imgSupplier, String tvSupplierName){
        this.imgSupplier=imgSupplier;
        this.tvSupplierName=tvSupplierName;



    }

    public HomeSupplierList(){

    }


    public String getimgSupplier() {
        return imgSupplier;
    }

    public String gettvSupplierName() {
        return tvSupplierName;
    }











}