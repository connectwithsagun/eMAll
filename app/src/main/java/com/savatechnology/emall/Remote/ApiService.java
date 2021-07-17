package com.savatechnology.emall.Remote;

import com.savatechnology.emall.JSONSchemas.FeaturedProduct;
import com.savatechnology.emall.JSONSchemas.NewArrivalProduct;
import com.savatechnology.emall.JSONSchemas.SupplierProduct;
import com.savatechnology.emall.JSONSchemas.Suppliers;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    @FormUrlEncoded
    @POST("auth/login")
    Call<ResponseBody> loginUser(@Field("email") String email,
                                 @Field("password") String password
    );

    @GET("user")
    Call<ResponseBody> getUserList();

    @FormUrlEncoded
    @POST("auth/register")
    Call<ResponseBody> registerUser(@Field("email") String email,
                                    @Field("password") String password
    );

    @FormUrlEncoded
    @POST("auth/verify-user/{email}")


    Call<ResponseBody> userRegistrationVerify(
            @Field("token") String token,
            @Path("email") String email
    );

    @FormUrlEncoded
    @POST("auth/forgot-password")
    Call<ResponseBody> forgotPassword(@Field("email") String email);

    @FormUrlEncoded
    @POST("auth/verify-token/{email}")
    Call<ResponseBody> forgetPasswordTokenVerify(
            @Field("token") String token,
            @Path("email") String email
    );

    @FormUrlEncoded
    @PUT("auth/changepassword/{email}")
    Call<ResponseBody> passwordChangeByForgetPassword(
            @Path("email") String email,
            @Field("password") String password
    );

    @FormUrlEncoded
    @PUT("auth/cpassword/{email}")
    Call<ResponseBody> passwordChange(
            @Path("email") String email,
            @Field("oldpassword") String oldpassword,
            @Field("newpassword") String newpassword
    );

    @FormUrlEncoded
    @PUT("user/detail/{id}")
    Call<ResponseBody> editUserInfo(
            @Path("id") String id,
            @Field("username") String username,
            @Field("phone") String phone,
            @Field("addres") String addres,
            @Field("gender") String gender

    );

    @FormUrlEncoded
    @POST("user/detail")
    Call<ResponseBody> userDetailAdd(
            @Field("userid") String userid,
            @Field("username") String username,
            @Field("phone") String phone,
            @Field("addres") String addres,
            @Field("gender") String gender


    );

    @GET("user/detail/{id}")
    Call<ResponseBody> userDetailsGet(
            @Path("id") String id


    );

    @GET("product")
    Call<List<FeaturedProduct>> getFeaturedProduct();

    @GET("supplier")
    Call<List<Suppliers>> getSuppliers();

    @GET("product/get-limit-product")
    Call<List<NewArrivalProduct>> getLatestProduct();

    @GET("supplier/{id}")
    Call<ResponseBody> getSupplierDetail(
            @Path("id") String id
    );

    @GET("product/supplier-id/{id}")
    Call<List<SupplierProduct>> getSupplierProduct(
            @Path("id") String id
            );

}
