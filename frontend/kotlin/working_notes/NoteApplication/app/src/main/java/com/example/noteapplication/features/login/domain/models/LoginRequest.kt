package com.example.noteapplication.features.login.domain.models

import com.google.gson.annotations.SerializedName

data class LoginRequest (
    @SerializedName("grant_type")
    var grantType: String,

    @SerializedName("client_id")
    var clientId: String,

    @SerializedName("client_secret")
    var clientSecret: String
)