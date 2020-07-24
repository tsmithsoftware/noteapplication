package com.example.noteapplication.domain.usecases

import com.example.noteapplication.domain.models.NoteModel
import com.example.noteapplication.domain.repositories.NoteRepository
import io.reactivex.Single
import javax.inject.Inject

class GetNotesUseCase @Inject constructor (private val notesRepo: NoteRepository): SingleUseCase<List<NoteModel>>{
    override fun execute(): Single<List<NoteModel>> {
        return notesRepo.getNotes()
    }
}