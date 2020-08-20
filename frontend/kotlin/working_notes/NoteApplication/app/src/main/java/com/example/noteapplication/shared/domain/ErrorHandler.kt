package com.example.noteapplication.shared.domain

interface ErrorHandler {
    fun getError(throwable: Throwable): ErrorEntity
}