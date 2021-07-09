package com.savatechnology.emall.Remote;

public class ApiUtil {

    public static  String baseUrl = "https://bca-ecommerce-project.herokuapp.com/api/v1/";

    public static ApiService getApiService(){
        return RetrofitClient.getRetrofitClient(baseUrl).create(ApiService.class);
    }
}
