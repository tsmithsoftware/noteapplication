package com.example.noteapplication.features.notes.domain.repositories

import com.example.noteapplication.features.notes.domain.models.NoteModel
import io.reactivex.Single

interface NoteRepository {
    fun getNotes(): Single<List<NoteModel>>
}