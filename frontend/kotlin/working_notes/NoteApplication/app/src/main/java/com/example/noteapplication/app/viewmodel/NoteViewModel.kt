package com.example.noteapplication.app.viewmodel

import com.example.noteapplication.domain.repositories.NoteRepository
import com.example.noteapplication.app.di.scopes.ActivityScope
import javax.inject.Inject

// @Inject tells Dagger how to create instances of LoginViewModel
@ActivityScope
class NoteViewModel @Inject constructor(
    private val noteRepository: NoteRepository
) {
    val stringValue = "hi"
}