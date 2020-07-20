package com.timsmith.notesapplication.domain.usecases

import io.reactivex.Single

interface SingleUseCase<R> {
    fun execute() : Single<R>
}