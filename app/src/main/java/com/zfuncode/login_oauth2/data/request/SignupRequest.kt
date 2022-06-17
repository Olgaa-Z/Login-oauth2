package com.zfuncode.login_oauth2.data.request

import com.google.gson.annotations.SerializedName

class SignupRequest (
    @SerializedName("full_name") var fullname: String,
    @SerializedName("email") var email: String,
    @SerializedName("password") var password: String,
    @SerializedName("phone_number") var phonenumber: Int,
    @SerializedName("address") var address: String,
    @SerializedName("image_url") var iamge_url: String
 )