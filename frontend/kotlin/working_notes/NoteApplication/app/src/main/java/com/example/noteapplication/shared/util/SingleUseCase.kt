package com.example.noteapplication.shared.util

import com.example.noteapplication.features.login.domain.models.LoginRequest
import com.example.noteapplication.features.notes.domain.models.NoteModel
import com.example.noteapplication.shared.domain.ResultOf
import io.reactivex.Single
import retrofit2.Call

interface ResultUseCase<R, IParams> {
    fun execute(params: IParams) : ResultOf<R>
}

interface SingleUseCase<R, IParams> {
    fun execute(params: IParams) : Single<R>
}

interface CallUseCase<R,IParams> {
    fun execute(params: IParams) : Call<R>
}

interface NullableCallUseCase<R,IParams> {
    fun execute(params: IParams) : Call<R>?
}

interface IParams
open class DeleteNoteParams(var id: Int) :
    IParams
open class AddNoteParams(var note: NoteModel) :
    IParams
open class EditNoteParams(var note: NoteModel) :
    IParams
open class LoginParams(var request: LoginRequest):
    IParams
open class NoParams: IParams
