package com.savatechnology.emall.Models;


public class HomeProductCollectionList {

    private String imgSupplier;
    private String tvProductName;





    public void setimgSupplier(String imgSupplier) {
        this.imgSupplier = imgSupplier;
    }

    public void settvProductName(String tvProductName) {
        this.tvProductName = tvProductName;
    }







    public HomeProductCollectionList(String imgSupplier, String tvProductName){
        this.imgSupplier=imgSupplier;
        this.tvProductName=tvProductName;


    }

    public HomeProductCollectionList(){

    }


    public String getimgSupplier() {
        return imgSupplier;
    }

    public String gettvProductName() {
        return tvProductName;
    }









}