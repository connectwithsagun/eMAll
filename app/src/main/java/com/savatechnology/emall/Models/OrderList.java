package com.savatechnology.emall.Models;


public class OrderList {

    private int imgProduct;
    private String tvProductTitle;
    private String tvSupplierName;
    private String tvProductStatusResult;




    public void setimgProduct(int imgProduct) {
        this.imgProduct = imgProduct;
    }

    public void settvProductTitle(String tvProductTitle) {
        this.tvProductTitle = tvProductTitle;
    }

    public void settvSupplierName(String tvSupplierName) {
        this.tvSupplierName = tvSupplierName;
    }

    public void settvProductStatusResult(String tvProductStatusResult) {
        this.tvProductStatusResult = tvProductStatusResult;
    }






    public OrderList(int imgProduct, String tvProductTitle, String tvSupplierName, String tvProductStatusResult){
        this.imgProduct=imgProduct;
        this.tvProductTitle=tvProductTitle;
        this.tvSupplierName=tvSupplierName;
        this.tvProductStatusResult=tvProductStatusResult;

    }

    public OrderList(){

    }


    public int getimgProduct() {
        return imgProduct;
    }

    public String gettvProductTitle() {
        return tvProductTitle;
    }

    public String gettvSupplierName() {
        return tvSupplierName;
    }



    public String gettvProductStatusResult() {
        return tvProductStatusResult;
    }





}