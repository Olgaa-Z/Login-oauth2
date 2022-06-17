package com.zfuncode.login_oauth2.data.response


import com.google.gson.annotations.SerializedName

data class ProductResponseItem(
    @SerializedName("base_price")
    val basePrice: Int,
    @SerializedName("category")
    val category: String,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image_name")
    val imageName: Any,
    @SerializedName("image_url")
    val imageUrl: Any,
    @SerializedName("location")
    val location: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("updatedAt")
    val updatedAt: String,
    @SerializedName("user_id")
    val userId: Int,
    @SerializedName("message")
    val message: String
)