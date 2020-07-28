package com.example.noteapplication.features.notes.domain.usecases

import io.reactivex.Single

interface SingleUseCase<R> {
    fun execute() : Single<R>
}