package com.example.noteapplication.features.login.data.mappers

import com.example.noteapplication.features.login.data.models.LoginRequestDataModel
import com.example.noteapplication.features.login.data.models.LoginResponseDataModel
import com.example.noteapplication.features.login.domain.models.LoginRequest
import com.example.noteapplication.features.login.domain.models.LoginResponse

class LoginMapper {
    fun toRequestModel(requestDataModel: LoginRequestDataModel): LoginRequest {
        return LoginRequest(
            grantType = requestDataModel.grantType,
            clientId = requestDataModel.clientId,
            clientSecret = requestDataModel.clientSecret
        )
    }

    fun toRequestDataModel(requestModel: LoginRequest): LoginRequestDataModel {
        return LoginRequestDataModel(
            grantType = requestModel.grantType,
            clientId = requestModel.clientId,
            clientSecret = requestModel.clientSecret
        )
    }

    fun toResponseModel(responseDataModel: LoginResponseDataModel): LoginResponse {
        return LoginResponse(
            accessToken = responseDataModel.accessToken,
            scope = responseDataModel.scope,
            tokenType = responseDataModel.tokenType,
            expiresIn = responseDataModel.expiresIn
        )
    }

    fun toResponseDataModel(responseModel: LoginResponse): LoginResponseDataModel {
        return LoginResponseDataModel(
            accessToken = responseModel.accessToken,
            expiresIn = responseModel.expiresIn,
            tokenType = responseModel.tokenType,
            scope = responseModel.scope
        )
    }
}