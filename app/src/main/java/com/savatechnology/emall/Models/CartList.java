package com.savatechnology.emall.Models;


public class CartList {

    private String imgProduct;
    private String tvProductTitle;
    private int tvProductPrice;
    private int tvShippingCost;





    public void setimgProduct(String imgProduct) {
        this.imgProduct = imgProduct;
    }

    public void settvProductTitle(String tvProductTitle) {
        this.tvProductTitle = tvProductTitle;
    }

    public void settvProductPrice(int tvProductPrice) {
        this.tvProductPrice = tvProductPrice;
    }

    public void setTvShippingCost(int tvShippingCost) {
        this.tvShippingCost = tvShippingCost;
    }




    public CartList(String imgProduct, String tvProductTitle, int tvProductPrice,int tvShippingCost){
        this.imgProduct=imgProduct;
        this.tvProductTitle=tvProductTitle;
        this.tvProductPrice=tvProductPrice;
        this.tvShippingCost=tvShippingCost;

    }

    public CartList(){

    }


    public String getimgProduct() {
        return imgProduct;
    }

    public String gettvProductTitle() {
        return tvProductTitle;
    }
    public int gettvProductPrice() {
        return tvProductPrice;
    }

    public int gettvShippingCost() {
        return tvShippingCost;
    }







}