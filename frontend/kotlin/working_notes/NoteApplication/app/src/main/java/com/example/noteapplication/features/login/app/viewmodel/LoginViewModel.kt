package com.example.noteapplication.features.login.app.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.noteapplication.features.login.domain.models.LoginRequest
import com.example.noteapplication.features.login.domain.models.LoginResponse
import com.example.noteapplication.features.login.domain.usecases.LoginUseCase
import com.example.noteapplication.shared.util.LoginParams
import com.example.noteapplication.shared.util.SessionManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
): ViewModel() {

    @Inject
    lateinit var manager: SessionManager

    fun login(grantType: String, clientId: String, clientSecret: String) {
        val params = LoginParams(
            LoginRequest(
                grantType = grantType,
                clientId = clientId,
                clientSecret = clientSecret
            )
        )
        loginUseCase.execute(params)?.enqueue(
            LoginCallback(manager)
        )

    }
}

/**
 * A callback used to post the login details back to the user once logged in
 */
class LoginCallback(private val manager: SessionManager) : Callback<LoginResponse> {
    override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
        t.message?.let { Log.d("Login Failure", it) }
    }

    override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
        response.body()?.let { responseBody ->
            responseBody.accessToken?.let {
                Log.d("Login success", it)
                manager.saveAuthToken(it)
            }
        }
    }
}