package com.example.noteapplication.features.login.data.repositories

import com.example.noteapplication.features.login.data.services.LoginService
import com.example.noteapplication.features.login.domain.models.LoginRequest
import com.example.noteapplication.features.login.domain.models.LoginResponse
import com.example.noteapplication.features.login.domain.repositories.LoginRepository
import retrofit2.Call
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val loginService: LoginService
): LoginRepository {
    override fun login(loginRequest: LoginRequest): Call<LoginResponse>? {
        return loginService.login(
            grant_type = loginRequest.grantType,
            client_id = loginRequest.clientId,
            client_secret = loginRequest.clientSecret
        )
    }
}