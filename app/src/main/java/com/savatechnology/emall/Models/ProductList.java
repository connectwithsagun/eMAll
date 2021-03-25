package com.savatechnology.emall.Models;


public class ProductList {

    private String imageProduct;
    private String tvProductTitle;
    private int tvProductPrice;
    private double productRating;
    private String tvSupplierName;




    public void setimageProduct(String imageProduct) {
        this.imageProduct = imageProduct;
    }

    public void settvProductTitle(String tvProductTitle) {
        this.tvProductTitle = tvProductTitle;
    }

    public void settvProductPrice(int tvProductPrice) {
        this.tvProductPrice = tvProductPrice;
    }
    public void setproductRating(double productRating){ this.productRating= productRating;}

    public void setTvSupplierName(String tvSupplierName) {
        this.tvSupplierName = tvSupplierName;
    }





    public ProductList(String imageProduct, String tvProductTitle, int tvProductPrice,double productRating,String tvSupplierName){
        this.imageProduct=imageProduct;
        this.tvProductTitle=tvProductTitle;
        this.tvProductPrice=tvProductPrice;
        this.productRating=productRating;
        this.tvSupplierName=tvSupplierName;

    }

    public ProductList(){

    }


    public String getimageProduct() {
        return imageProduct;
    }

    public String gettvProductTitle() {
        return tvProductTitle;
    }
    public int gettvProductPrice() {
        return tvProductPrice;
    }

    public double getproductRating() {
        return productRating;
    }

    public String gettvSupplierName() {
        return tvSupplierName;
    }








}