package com.zfuncode.login_oauth2.data.request


import com.google.gson.annotations.SerializedName

data class LoginRequest(

    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String
)