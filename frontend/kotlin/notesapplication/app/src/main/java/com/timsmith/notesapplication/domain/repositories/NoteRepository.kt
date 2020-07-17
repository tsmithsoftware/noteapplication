package com.timsmith.notesapplication.domain.repositories

import com.timsmith.notesapplication.domain.models.NoteModel
import io.reactivex.Single

interface NoteRepository {
    fun getNotes(): Single<List<NoteModel>>
}