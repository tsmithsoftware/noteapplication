
package com.example.noteapplication.features.login.data.mappers

import com.example.noteapplication.features.login.data.models.LoginRequestDataModel
import com.example.noteapplication.features.login.data.models.LoginResponseDataModel
import com.example.noteapplication.features.login.domain.models.LoginRequest
import com.example.noteapplication.features.login.domain.models.LoginResponse
import org.junit.Test

class LoginMapperTest {

    private val grantType = "client_credentials"
    private val clientId = "oAuthClient"
    private val clientSecret = "client_secret"

    private val accessToken = "access_token"
    private val expiresIn = "3600"
    private val tokenType = "Bearer"
    private val scope = "notesApi.all"

    private val requestDataModel = LoginRequestDataModel(
        grantType = grantType,
        clientSecret = clientSecret,
        clientId = clientId
    )
    private val requestModel = LoginRequest(
        grantType = grantType,
        clientSecret = clientSecret,
        clientId = clientId
    )

    private val responseDataModel = LoginResponseDataModel(
        accessToken = accessToken,
        expiresIn = expiresIn,
        tokenType = tokenType,
        scope = scope
    )
    private val responseModel = LoginResponse(
        accessToken = accessToken,
        expiresIn = expiresIn,
        tokenType = tokenType,
        scope = scope
    )

    @Test
    fun testLoginMapperCorrectlyMapsRequestDataModelToModel() {
        val result: LoginRequest = LoginMapper().toRequestModel(requestDataModel)
        assert(result.clientId == requestDataModel.clientId)
        assert(result.clientSecret == requestDataModel.clientSecret)
        assert(result.grantType == requestDataModel.grantType)
    }

    @Test
    fun testLoginMapperCorrectlyMapsRequestModelToDataModel() {
        val result: LoginRequestDataModel = LoginMapper().toRequestDataModel(requestModel)
        assert(result.clientId == requestModel.clientId)
        assert(result.clientSecret == requestModel.clientSecret)
        assert(result.grantType == requestModel.grantType)
    }

    @Test
    fun testLoginMapperCorrectlyMapsResponseDataModelToModel() {
        val result: LoginResponse = LoginMapper().toResponseModel(responseDataModel)
        assert(result.accessToken == responseDataModel.accessToken)
        assert(result.expiresIn == responseDataModel.expiresIn)
        assert(result.scope == responseDataModel.scope)
        assert(result.tokenType == responseDataModel.tokenType)
    }

    @Test
    fun testLoginMapperCorrectlyMapsResponseModelToDataModel() {
        val result: LoginResponseDataModel = LoginMapper().toResponseDataModel(responseModel)
        assert(result.accessToken == responseModel.accessToken)
        assert(result.expiresIn == responseModel.expiresIn)
        assert(result.scope == responseModel.scope)
        assert(result.tokenType == responseModel.tokenType)
    }
}