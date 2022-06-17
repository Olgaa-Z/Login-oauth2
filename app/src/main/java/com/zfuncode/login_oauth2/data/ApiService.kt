package com.zfuncode.login_oauth2.data

import com.zfuncode.login_oauth2.data.request.LoginRequest
import com.zfuncode.login_oauth2.data.request.SignupRequest
import com.zfuncode.login_oauth2.data.response.LoginResponse
import com.zfuncode.login_oauth2.data.response.ProductResponseItem
import com.zfuncode.login_oauth2.data.response.RegisterResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @POST("auth/login")
    fun login(@Body request : LoginRequest): Call<LoginResponse>

    @GET("seller/product")
    fun allProduct():Call<ProductResponseItem>


    @POST("auth/register")
    fun register( @Body signupRequest: SignupRequest):Call<RegisterResponse>

//    @GET("seller/product")
//    fun allProduct(@Header("Authorization") token: String):Call<ProductResponseItem>
}