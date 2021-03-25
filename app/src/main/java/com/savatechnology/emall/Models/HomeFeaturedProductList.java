package com.savatechnology.emall.Models;


public class HomeFeaturedProductList {

    private String imgSupplier;
    private String tvProductName;
    private int tvProductPrice;





    public void setimgSupplier(String imgSupplier) {
        this.imgSupplier = imgSupplier;
    }

    public void settvProductName(String tvProductName) {
        this.tvProductName = tvProductName;
    }

    public void settvProductPrice(int tvProductPrice) {
        this.tvProductPrice = tvProductPrice;
    }








    public HomeFeaturedProductList(String imgSupplier, String tvProductName, int tvProductPrice){
        this.imgSupplier=imgSupplier;
        this.tvProductName=tvProductName;
        this.tvProductPrice=tvProductPrice;


    }

    public HomeFeaturedProductList(){

    }


    public String getimgSupplier() {
        return imgSupplier;
    }

    public String gettvProductName() {
        return tvProductName;
    }

    public int gettvProductPrice() {
        return tvProductPrice;
    }










}