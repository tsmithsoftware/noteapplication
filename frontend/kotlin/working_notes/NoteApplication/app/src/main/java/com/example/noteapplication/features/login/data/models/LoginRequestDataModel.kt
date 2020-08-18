package com.example.noteapplication.features.login.data.models

import com.google.gson.annotations.SerializedName

data class LoginRequestDataModel(
    @SerializedName("grant_type")
    val grantType: String = "",
    @SerializedName("client_id")
    val clientId: String = "",
    @SerializedName("client_secret")
    val clientSecret: String = ""
)