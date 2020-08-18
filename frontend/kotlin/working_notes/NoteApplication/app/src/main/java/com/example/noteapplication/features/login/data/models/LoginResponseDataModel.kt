package com.example.noteapplication.features.login.data.models

import com.google.gson.annotations.SerializedName

data class LoginResponseDataModel (
    @SerializedName("access_token")
    val accessToken: String? = null,
    @SerializedName("expires_in")
    val expiresIn: String? = null,
    @SerializedName("token_type")
    val tokenType: String? = null,
    @SerializedName("scope")
    val scope: String? = null
)