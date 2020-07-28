package com.example.noteapplication.features.notes.data.datasources

import com.example.noteapplication.features.notes.domain.models.NoteModel
import io.reactivex.Single

interface NoteLocalDataSource {
    fun getNotes(): Single<List<NoteModel>>
    fun cacheNotes(noteModel: NoteModel)
}