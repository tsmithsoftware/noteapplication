package com.example.noteapplication.features.login.domain.repositories

import com.example.noteapplication.features.login.domain.models.LoginRequest
import com.example.noteapplication.features.login.domain.models.LoginResponse
import retrofit2.Call

interface LoginRepository {
    fun login(loginRequest: LoginRequest): Call<LoginResponse>?
}