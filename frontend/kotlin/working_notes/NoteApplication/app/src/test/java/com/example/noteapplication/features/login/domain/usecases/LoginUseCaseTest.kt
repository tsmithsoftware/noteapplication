package com.example.noteapplication.features.login.domain.usecases

import com.example.noteapplication.features.login.domain.models.LoginRequest
import com.example.noteapplication.features.login.domain.models.LoginResponse
import com.example.noteapplication.features.login.domain.repositories.LoginRepository
import com.example.noteapplication.shared.util.LoginParams
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import org.junit.Before
import org.junit.Test
import retrofit2.Call

class LoginUseCaseTest {
    private val mockLoginRepo:LoginRepository = mock()
    private val mockSingle: Call<LoginResponse> = mock()
    private val loginRequest: LoginRequest = LoginRequest(
        grantType = "client_credetials",
        clientId = "clientName",
        clientSecret = "clientSecret"
        )
    private val params: LoginParams = LoginParams(loginRequest)

    @Before
    fun setup() {
        whenever(mockLoginRepo.login(loginRequest)).thenReturn(mockSingle)
    }

    @Test
    fun testUseCaseCallsRepoGetNotesMethod() {
        val sut = LoginUseCase(mockLoginRepo)
        sut.execute(params)
        verify(mockLoginRepo).login(loginRequest)
    }
}