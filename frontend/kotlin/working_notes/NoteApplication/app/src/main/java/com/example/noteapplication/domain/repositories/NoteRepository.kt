package com.example.noteapplication.domain.repositories

import com.example.noteapplication.domain.models.NoteModel
import io.reactivex.Single

interface NoteRepository {
    fun getNotes(): Single<List<NoteModel>>
}