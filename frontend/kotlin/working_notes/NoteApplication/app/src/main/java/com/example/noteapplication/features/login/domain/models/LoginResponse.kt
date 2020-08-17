package com.example.noteapplication.features.login.domain.models

import com.google.gson.annotations.SerializedName

data class LoginResponse (
    @SerializedName("access_token")
    var accessToken: String,

    @SerializedName("expires_in")
    var expiresIn: String ,

    @SerializedName("token_type")
    var tokenType: String,

    @SerializedName("scope")
    var scope: String
)