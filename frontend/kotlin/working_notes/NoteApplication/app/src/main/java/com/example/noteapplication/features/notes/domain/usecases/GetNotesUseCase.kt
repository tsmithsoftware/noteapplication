package com.example.noteapplication.features.notes.domain.usecases

import com.example.noteapplication.features.notes.domain.models.NoteModel
import com.example.noteapplication.features.notes.domain.repositories.NoteRepository
import com.example.noteapplication.shared.domain.ResultOf
import com.example.noteapplication.shared.util.NoParams
import com.example.noteapplication.shared.util.ResultUseCase
import javax.inject.Inject

class GetNotesUseCase @Inject constructor (private val notesRepo: NoteRepository):
    ResultUseCase<List<NoteModel>, NoParams> {
    override fun execute(params: NoParams): ResultOf<List<NoteModel>> {
        return notesRepo
            .getNotes()
    }
}