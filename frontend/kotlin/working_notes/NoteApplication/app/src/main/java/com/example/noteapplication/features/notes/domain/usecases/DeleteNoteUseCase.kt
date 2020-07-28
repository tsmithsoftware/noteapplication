package com.example.noteapplication.features.notes.domain.usecases

import com.example.noteapplication.features.notes.domain.repositories.NoteRepository
import retrofit2.Call
import javax.inject.Inject

class DeleteNoteUseCase @Inject constructor (private val notesRepo: NoteRepository):
    CallUseCase<Void, Params> {
    override fun execute(params: Params): Call<Void> {
        return notesRepo.deleteNote(params.id)
    }
}