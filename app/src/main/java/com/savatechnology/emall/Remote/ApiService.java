package com.savatechnology.emall.Remote;

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
    @POST("auth/forgot-password")
    Call<ResponseBody> forgotPassword(@Field("email") String email);

    @FormUrlEncoded
    @POST("auth/verify-token")
    Call<ResponseBody> forgetPasswordTokenVerify(@Field("token") String email);

    @FormUrlEncoded
    @PUT("auth/changepassword/{email}")
    Call<ResponseBody> passwordChangeByForgetPassword(
            @Query("email") String email,
            @Field("password") String password
    );

}
