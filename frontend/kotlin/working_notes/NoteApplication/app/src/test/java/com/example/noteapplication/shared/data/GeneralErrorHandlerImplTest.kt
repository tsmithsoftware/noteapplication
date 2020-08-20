package com.example.noteapplication.shared.data

import com.example.noteapplication.shared.domain.ErrorEntity
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import org.junit.Test
import retrofit2.HttpException
import java.io.IOException

class GeneralErrorHandlerImplTest {

    private val ioThrowable: IOException = mock()
    private val genericThrowable: Throwable = mock()
    private val httpException: HttpException = mock()

    @Test
    fun testGenericThrowableReturnsErrorEntityUnknown() {
        val errorEntity = GeneralErrorHandlerImpl.getError(
            genericThrowable
        )
        assert (errorEntity is ErrorEntity.Unknown)
    }

    @Test
    fun testIoExceptionReturnsNetworkEntity() {
        val errorEntity = GeneralErrorHandlerImpl.getError(ioThrowable)
        assert(errorEntity is ErrorEntity.Network)
    }

    @Test
    fun testHttpUnknownErrorCodeReturnsUnknownErrorEntity() {
        whenever(httpException.code()).thenReturn(12345)
        val errorEntity = GeneralErrorHandlerImpl.getError(httpException)
        assert(errorEntity is ErrorEntity.Unknown)
    }

    @Test
    fun testHttpErrorCode404Returns404ErrorEntity() {
        whenever(httpException.code()).thenReturn(404)
        val errorEntity = GeneralErrorHandlerImpl.getError(httpException)
        assert(errorEntity is ErrorEntity.NotFound)
    }

    @Test
    fun testHttpCode403Returns403ErrorEntity() {
        whenever(httpException.code()).thenReturn(403)
        val errorEntity = GeneralErrorHandlerImpl.getError(httpException)
        assert(errorEntity is ErrorEntity.AccessDenied)
    }

    @Test
    fun testHttpCode503Returns504ErrorEntity() {
        whenever(httpException.code()).thenReturn(503)
        val errorEntity = GeneralErrorHandlerImpl.getError(httpException)
        assert(errorEntity is ErrorEntity.ServiceUnavailable)
    }
}