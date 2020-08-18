package com.example.noteapplication.features.notes.app.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.noteapplication.features.notes.domain.models.NoteModel
import com.example.noteapplication.shared.util.EditNoteParams
import com.example.noteapplication.features.notes.domain.usecases.EditNoteUseCase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class EditNoteViewModel @Inject constructor(
    private val editNoteUseCase: EditNoteUseCase
): ViewModel() {

    @Inject
    lateinit var noteViewModel: NoteViewModel

    fun editNote(note: NoteModel) {
        editNoteUseCase.execute(
            EditNoteParams(
                note
            )
        )?.enqueue(
            EditCallback(noteViewModel)
        )
    }
}

/**
 * A callback used to reload the Notes once a Edit call is made
 */
class EditCallback(private val noteViewModel: NoteViewModel) : Callback<Void> {
    override fun onFailure(call: Call<Void>, t: Throwable) {
        Log.d("failure call", "${t.message}")
    }

    override fun onResponse(call: Call<Void>, response: Response<Void>) {
        Log.d("response call success", "success")
        noteViewModel.loadNotes()
    }
}