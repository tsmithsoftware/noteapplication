package com.example.noteapplication.features.notes.app.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.noteapplication.features.notes.domain.models.NoteModel
import com.example.noteapplication.features.notes.domain.usecases.DeleteNoteUseCase
import com.example.noteapplication.features.notes.domain.usecases.GetNotesUseCase
import com.example.noteapplication.shared.di.scopes.ActivityScope
import com.example.noteapplication.shared.domain.ResultOf
import com.example.noteapplication.shared.util.DeleteNoteParams
import com.example.noteapplication.shared.util.NoParams
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@ActivityScope
class NoteViewModel @Inject constructor(
    private val getNotesUseCase: GetNotesUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase
): ViewModel() {
    private val notes: MutableLiveData<List<NoteModel>> by lazy {
        MutableLiveData<List<NoteModel>>().also {
            loadNotes()
        }
    }

    fun getNotes(): LiveData<List<NoteModel>> {
        return notes
    }

    fun loadNotes() {
        when(val result = getNotesUseCase.execute(NoParams())) {
            is ResultOf.Success -> {
                notes.value = result.value
            }
            is ResultOf.Failure -> {
                //do something
            }
        }
    }

    fun onDeleteNoteClicked(noteId: Int?) {
        noteId?.let {
            deleteNoteUseCase.execute(
                DeleteNoteParams(
                    noteId
                )
            ).enqueue(
                DeleteCallback(this)
            )
        }
    }

}

/**
 * A callback used to reload the Notes once a Delete call is made
 */
class DeleteCallback(private val noteViewModel: NoteViewModel) : Callback<Void> {
    override fun onFailure(call: Call<Void>, t: Throwable) {
        Log.d("failure call", "${t.message}")
    }

    override fun onResponse(call: Call<Void>, response: Response<Void>) {
        Log.d("response call success", "success")
        noteViewModel.loadNotes()
    }
}