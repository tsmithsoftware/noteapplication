package com.example.noteapplication.features.notes.domain.usecases

import com.example.noteapplication.features.notes.domain.repositories.NoteRepository
import com.example.noteapplication.shared.util.CallUseCase
import com.example.noteapplication.shared.util.DeleteNoteParams
import retrofit2.Call
import javax.inject.Inject

class DeleteNoteUseCase @Inject constructor (private val notesRepo: NoteRepository):
    CallUseCase<Void, DeleteNoteParams> {
    override fun execute(params: DeleteNoteParams): Call<Void> {
        return notesRepo.deleteNote(params.id)
    }
}