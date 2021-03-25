package com.savatechnology.emall.Models;


public class HomeJustForYouList {

    private String imgProduct;
    private String tvProductName;
    private int tvProductPrice;





    public void setimgProduct(String imgProduct) {
        this.imgProduct = imgProduct;
    }

    public void settvProductName(String tvProductName) {
        this.tvProductName = tvProductName;
    }

    public void settvProductPrice(int tvProductPrice) {
        this.tvProductPrice = tvProductPrice;
    }








    public HomeJustForYouList(String imgProduct, String tvProductName, int tvProductPrice){
        this.imgProduct=imgProduct;
        this.tvProductName=tvProductName;
        this.tvProductPrice=tvProductPrice;


    }

    public HomeJustForYouList(){

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