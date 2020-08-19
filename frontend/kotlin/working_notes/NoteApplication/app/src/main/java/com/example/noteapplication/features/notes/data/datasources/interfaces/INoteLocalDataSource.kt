package com.example.noteapplication.features.notes.data.datasources.interfaces

import com.example.noteapplication.features.notes.data.datasources.interfaces.INoteDataSource
import com.example.noteapplication.features.notes.data.models.NoteDataModel
import com.example.noteapplication.features.notes.data.models.PostNote
import io.reactivex.Single
import retrofit2.Call

interface INoteLocalDataSource: INoteDataSource {
    override fun getNotes(): Single<List<NoteDataModel>>
    override fun deleteNote(noteId: Int): Call<Void>
    override fun editNote(noteId: Int, note: PostNote): Call<Void>
    override fun postNote(note: PostNote): Call<Void>
    fun cacheNotes(noteModel: List<NoteDataModel>)
}