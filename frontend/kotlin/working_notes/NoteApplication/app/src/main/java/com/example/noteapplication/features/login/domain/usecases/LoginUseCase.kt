package com.example.noteapplication.features.login.domain.usecases

import com.example.noteapplication.features.login.domain.models.LoginResponse
import com.example.noteapplication.features.login.domain.repositories.LoginRepository
import com.example.noteapplication.shared.util.LoginParams
import com.example.noteapplication.shared.util.NullableCallUseCase
import retrofit2.Call
import javax.inject.Inject

class LoginUseCase @Inject constructor (private val loginRepo: LoginRepository):
    NullableCallUseCase<LoginResponse, LoginParams> {
    override fun execute(params: LoginParams): Call<LoginResponse>? {
        return loginRepo.login(params.request)
    }
}