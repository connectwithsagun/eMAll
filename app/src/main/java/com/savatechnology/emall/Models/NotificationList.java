package com.savatechnology.emall.Models;


public class NotificationList {

    private int imgNotification;
    private String tvNotificationTitle;
    private String tvDate;
    private String tvNotificationDescription;




    public void setimgNotification(int imgNotification) {
        this.imgNotification = imgNotification;
    }

    public void settvNotificationTitle(String tvNotificationTitle) {
        this.tvNotificationTitle = tvNotificationTitle;
    }

    public void settvDate(String tvDate) {
        this.tvDate = tvDate;
    }

    public void settvNotificationDescription(String tvNotificationDescription) {
        this.tvNotificationDescription = tvNotificationDescription;
    }






    public NotificationList(int imgNotification, String tvNotificationTitle, String tvDate, String tvNotificationDescription){
        this.imgNotification=imgNotification;
        this.tvNotificationTitle=tvNotificationTitle;
        this.tvDate=tvDate;
        this.tvNotificationDescription=tvNotificationDescription;

    }

    public NotificationList(){

    }


    public int getimgNotification() {
        return imgNotification;
    }

    public String gettvNotificationTitle() {
        return tvNotificationTitle;
    }

    public String gettvDate() {
        return tvDate;
    }



    public String gettvNotificationDescription() {
        return tvNotificationDescription;
    }





}