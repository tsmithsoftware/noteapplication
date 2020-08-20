package com.example.noteapplication.shared.data

import com.example.noteapplication.shared.domain.ErrorEntity
import com.example.noteapplication.shared.domain.ErrorHandler
import retrofit2.HttpException
import java.io.IOException
import java.net.HttpURLConnection

object GeneralErrorHandlerImpl: ErrorHandler {
    override fun getError(throwable: Throwable): ErrorEntity {
        return when (throwable) {
            is IOException -> ErrorEntity.Network
            is HttpException -> {
                when (throwable.code()) {
                    // not found
                    HttpURLConnection.HTTP_NOT_FOUND -> ErrorEntity.NotFound
                    // access denied
                    HttpURLConnection.HTTP_FORBIDDEN -> ErrorEntity.AccessDenied
                    // unavailable service
                    HttpURLConnection.HTTP_UNAVAILABLE -> ErrorEntity.ServiceUnavailable
                    else -> ErrorEntity.Unknown
                }
            }
            else -> ErrorEntity.Unknown
        }
    }
}