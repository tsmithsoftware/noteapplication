package com.example.noteapplication.features.notes.domain.usecases

import io.reactivex.Single

interface SingleUseCase<R, IParams> {
    fun execute(params: IParams) : Single<R>
}

interface IParams
open class Params(var id: Int) : IParams
open class NoParams: IParams
