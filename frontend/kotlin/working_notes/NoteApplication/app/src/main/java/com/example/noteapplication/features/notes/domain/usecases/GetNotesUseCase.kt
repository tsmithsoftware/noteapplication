package com.example.noteapplication.features.notes.domain.usecases

import com.example.noteapplication.features.notes.domain.models.NoteModel
import com.example.noteapplication.features.notes.domain.repositories.NoteRepository
import com.example.noteapplication.shared.util.NoParams
import com.example.noteapplication.shared.util.SingleUseCase
import io.reactivex.Single
import javax.inject.Inject

class GetNotesUseCase @Inject constructor (private val notesRepo: NoteRepository):
    SingleUseCase<List<NoteModel>, NoParams> {
    override fun execute(params: NoParams): Single<List<NoteModel>> {
        return notesRepo.getNotes()
    }
}