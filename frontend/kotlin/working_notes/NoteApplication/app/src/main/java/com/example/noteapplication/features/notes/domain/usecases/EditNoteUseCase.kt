package com.example.noteapplication.features.notes.domain.usecases

import com.example.noteapplication.features.notes.domain.repositories.NoteRepository
import retrofit2.Call
import javax.inject.Inject

class EditNoteUseCase @Inject constructor(private val notesRepository: NoteRepository): NullableCallUseCase<Void, EditNoteParams> {
    override fun execute(params: EditNoteParams): Call<Void>? {
        return notesRepository.editNote(params)
    }
}