package com.example.noteapplication.app.viewmodel

import com.example.noteapplication.app.di.scopes.ActivityScope
import com.example.noteapplication.domain.usecases.GetNotesUseCase
import javax.inject.Inject

@ActivityScope
class NoteViewModel @Inject constructor(
    notesUseCase: GetNotesUseCase
) {
    val notes = notesUseCase.execute()
}