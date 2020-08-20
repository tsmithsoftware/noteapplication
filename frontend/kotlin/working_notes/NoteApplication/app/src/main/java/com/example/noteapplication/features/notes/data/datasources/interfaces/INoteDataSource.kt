package com.example.noteapplication.features.notes.data.datasources.interfaces

import com.example.noteapplication.features.notes.data.models.NoteDataModel
import com.example.noteapplication.features.notes.data.models.PostNote
import io.reactivex.Single
import retrofit2.Call

interface INoteDataSource {
    fun getNotes(): Single<List<NoteDataModel>>
    fun deleteNote(noteId: Int): Call<Void>
    fun postNote(note: PostNote): Call<Void>
    fun editNote(noteId: Int, note: PostNote): Call<Void>
}