package com.example.noteapplication.features.notes.domain.usecases

import com.example.noteapplication.features.notes.domain.repositories.NoteRepository
import com.example.noteapplication.shared.util.AddNoteParams
import com.example.noteapplication.shared.util.CallUseCase
import retrofit2.Call
import javax.inject.Inject

class PostNotesUseCase @Inject constructor(private val notesRepository: NoteRepository) :
    CallUseCase<Void, AddNoteParams> {
    override fun execute(params: AddNoteParams): Call<Void> {
        return notesRepository.postNote(params.note)
    }
}