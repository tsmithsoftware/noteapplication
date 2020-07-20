package com.timsmith.notesapplication.domain.usecases

import com.timsmith.notesapplication.domain.models.NoteModel
import com.timsmith.notesapplication.domain.repositories.NoteRepository
import io.reactivex.Single
import javax.inject.Inject

class GetNotesUseCase @Inject constructor (private val notesRepo: NoteRepository): SingleUseCase<List<NoteModel>>{
    override fun execute(): Single<List<NoteModel>> {
        return notesRepo.getNotes()
    }
}