package com.example.noteapplication.features.notes.app.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.noteapplication.features.notes.domain.models.NoteModel
import com.example.noteapplication.features.notes.domain.usecases.AddNoteParams
import com.example.noteapplication.features.notes.domain.usecases.PostNotesUseCase
import com.example.noteapplication.shared.di.scopes.ActivityScope
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@ActivityScope
class SubmitNoteViewModel @Inject constructor(
    private val postNotesUseCase: PostNotesUseCase
): ViewModel(){

    @Inject
    lateinit var noteViewModel: NoteViewModel

    fun submitNote(note: NoteModel) {
        postNotesUseCase.execute(AddNoteParams(note)).enqueue(
            PostNoteCallback(noteViewModel)
        )
    }
}

/**
 * A callback used to reload the Notes once a Post call is made
 */
class PostNoteCallback(private val noteViewModel: NoteViewModel) : Callback<Void> {
    override fun onFailure(call: Call<Void>, t: Throwable) {
        Log.d("failure call", "${t.message}")
    }

    override fun onResponse(call: Call<Void>, response: Response<Void>) {
        Log.d("response call success", "success")
        noteViewModel.loadNotes()
    }
}