package com.savatechnology.emall.Models;


public class HomeNewArrivalsProductList {

    private String imgProduct;
    private String tvProductName;
    private int tvProductPrice;





    public void setimgSupplier(String imgProduct) {
        this.imgProduct = imgProduct;
    }

    public void settvProductName(String tvProductName) {
        this.tvProductName = tvProductName;
    }

    public void settvProductPrice(int tvProductPrice) {
        this.tvProductPrice = tvProductPrice;
    }








    public HomeNewArrivalsProductList(String imgProduct, String tvProductName, int tvProductPrice){
        this.imgProduct=imgProduct;
        this.tvProductName=tvProductName;
        this.tvProductPrice=tvProductPrice;


    }

    public HomeNewArrivalsProductList(){

    }


    public String getimgProduct() {
        return imgProduct;
    }

    public String gettvProductName() {
        return tvProductName;
    }

    public int gettvProductPrice() {
        return tvProductPrice;
    }










}