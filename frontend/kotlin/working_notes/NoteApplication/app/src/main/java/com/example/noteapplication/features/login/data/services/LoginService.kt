package com.example.noteapplication.features.login.data.services

import com.example.noteapplication.features.login.domain.models.LoginResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface LoginService {
    @FormUrlEncoded
    @POST("/oauth")
    fun login(
        @Field("grant_type") grant_type: String,
        @Field("client_id") client_id: String,
        @Field("client_secret") client_secret: String
    ): Call<LoginResponse>
}