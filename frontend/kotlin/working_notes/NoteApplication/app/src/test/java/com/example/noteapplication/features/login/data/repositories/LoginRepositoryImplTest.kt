package com.example.noteapplication.features.login.data.repositories

import com.example.noteapplication.features.login.data.services.LoginService
import com.example.noteapplication.features.login.domain.models.LoginRequest
import com.example.noteapplication.features.login.domain.models.LoginResponse
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import org.junit.Before
import org.junit.Test
import retrofit2.Call

class LoginRepositoryImplTest {
    private lateinit var sut: LoginRepositoryImpl
    private val mockLoginService: LoginService = mock()
    private val mockCall: Call<LoginResponse> = mock()
    private val loginRequest = LoginRequest(
        grantType = "client_credentials",
        clientId = "clientid",
        clientSecret = "clientsecret"
    )

    @Before
    fun setUp() {
        whenever(mockLoginService.login(any(), any(), any())).thenReturn(mockCall)
        sut = LoginRepositoryImpl(mockLoginService)
    }

    @Test
    fun testLoginCorrectlyCallsLoginService() {
        sut.login(loginRequest)
        verify(mockLoginService).login(loginRequest.grantType, loginRequest.clientId, loginRequest.clientSecret)
    }
}