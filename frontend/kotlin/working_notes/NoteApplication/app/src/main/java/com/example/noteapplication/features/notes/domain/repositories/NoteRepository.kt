package com.example.noteapplication.features.notes.domain.repositories

import com.example.noteapplication.features.notes.domain.models.NoteModel
import com.example.noteapplication.shared.domain.ResultOf
import com.example.noteapplication.shared.util.EditNoteParams
import retrofit2.Call

interface NoteRepository {
    fun getNotes(): ResultOf<List<NoteModel>>
    fun deleteNote(noteId: Int): Call<Void>
    fun postNote(note:NoteModel): Call<Void>
    fun editNote(params: EditNoteParams): Call<Void>?
}