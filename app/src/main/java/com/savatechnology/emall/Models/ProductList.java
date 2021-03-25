package com.savatechnology.emall.Models;


public class ProductList {

    private int imageProduct;
    private String tvProductName;
    private String tvProductPrice;





    public void setImageProduct(int imageProduct) {
        this.imageProduct = imageProduct;
    }

    public void setTvProductName(String tvProductName) {
        this.tvProductName = tvProductName;
    }

    public void setTvProductPrice(String tvProductPrice) {
        this.tvProductPrice = tvProductPrice;
    }






    public ProductList(int imageProduct, String tvProductName, String tvProductPrice){
        this.imageProduct=imageProduct;
        this.tvProductName=tvProductName;
        this.tvProductPrice=tvProductPrice;


    }

    public ProductList(){

    }


    public int getimageProduct() {
        return imageProduct;
    }

    public String gettvProductName() {
        return tvProductName;
    }

    public String gettvProductPrice() {
        return tvProductPrice;
    }








}