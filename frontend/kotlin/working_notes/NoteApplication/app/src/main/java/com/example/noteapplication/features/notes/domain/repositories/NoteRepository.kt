package com.example.noteapplication.features.notes.domain.repositories

import com.example.noteapplication.features.notes.domain.models.NoteModel
import io.reactivex.Single
import retrofit2.Call

interface NoteRepository {
    fun getNotes(): Single<List<NoteModel>>
    fun deleteNote(noteId: Int): Call<Void>
}