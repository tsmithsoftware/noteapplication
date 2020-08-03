package com.example.noteapplication.features.notes.domain.repositories

import com.example.noteapplication.features.notes.data.models.NoteDataModel
import com.example.noteapplication.features.notes.domain.models.NoteModel
import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.Call

interface NoteRepository {
    fun getNotes(): Single<List<NoteModel>>
    fun deleteNote(noteId: Int): Call<Void>
    fun postNote(note:NoteModel): Call<Void>
}