package com.savatechnology.emall.Models;

public class SliderImageList {

    private String description;
    private String imageUrl;

    SliderImageList(){

    }
    public SliderImageList(String description, String img){
        this.description = description;
        this.imageUrl = img;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}