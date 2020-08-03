package com.example.noteapplication.features.notes.app.viewmodel

import androidx.lifecycle.ViewModel
import com.example.noteapplication.features.notes.domain.models.NoteModel
import com.example.noteapplication.features.notes.domain.usecases.AddNoteParams
import com.example.noteapplication.features.notes.domain.usecases.PostNotesUseCase
import com.example.noteapplication.shared.di.scopes.ActivityScope
import javax.inject.Inject

@ActivityScope
class SubmitNoteViewModel @Inject constructor(
    private val postNotesUseCase: PostNotesUseCase
): ViewModel(){
    fun submitNote(note: NoteModel) {
        postNotesUseCase.execute(AddNoteParams(note))
    }
}