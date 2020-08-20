package com.example.noteapplication.shared.domain

sealed class ResultOf<T> {
    data class Success<T>(val value: T): ResultOf<T>()
    data class Failure<T>(
        val errorEntity: ErrorEntity
    ): ResultOf<T>()
}