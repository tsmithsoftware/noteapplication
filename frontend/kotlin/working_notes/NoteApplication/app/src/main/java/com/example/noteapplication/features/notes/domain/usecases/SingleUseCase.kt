package com.example.noteapplication.features.notes.domain.usecases

import io.reactivex.Single
import retrofit2.Call

interface SingleUseCase<R, IParams> {
    fun execute(params: IParams) : Single<R>
}

interface CallUseCase<R,IParams> {
    fun execute(params: IParams) : Call<R>
}

interface IParams
open class Params(var id: Int) : IParams
open class NoParams: IParams
