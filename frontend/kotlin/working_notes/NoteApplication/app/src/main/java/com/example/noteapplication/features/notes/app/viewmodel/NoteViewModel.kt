package com.example.noteapplication.features.notes.app.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.noteapplication.shared.di.scopes.ActivityScope
import com.example.noteapplication.features.notes.domain.models.NoteModel
import com.example.noteapplication.features.notes.domain.usecases.GetNotesUseCase
import com.example.noteapplication.features.notes.domain.usecases.NoParams
import io.reactivex.Observer
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@ActivityScope
class NoteViewModel @Inject constructor(
    val notesUseCase: GetNotesUseCase
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
        notesUseCase.execute(NoParams())
            .subscribeOn(Schedulers.io())
            .subscribeWith(object: DisposableSingleObserver<List<NoteModel>>(),
                Observer<NoteModel> {
                override fun onSuccess(obtainedNotes: List<NoteModel>?) {
                    notes.postValue(obtainedNotes)
                }

                override fun onError(e: Throwable?) {}
                override fun onComplete() {}
                override fun onNext(value: NoteModel?) {}
            })
    }

    fun onDeleteNoteClicked(noteId: Int?) {

    }

}