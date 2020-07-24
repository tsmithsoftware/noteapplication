package com.example.noteapplication.app.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.noteapplication.app.di.scopes.ActivityScope
import com.example.noteapplication.app.ui.NoteAdapter
import com.example.noteapplication.domain.models.NoteModel
import com.example.noteapplication.domain.usecases.GetNotesUseCase
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

    private fun loadNotes() {
        notesUseCase.execute()
            .subscribeOn(Schedulers.io())
            .subscribeWith(object: DisposableSingleObserver<List<NoteModel>>(),
                Observer<NoteModel> {
                override fun onSuccess(obtainedNotes: List<NoteModel>?) {
                    notes.postValue(obtainedNotes)
                }

                override fun onError(e: Throwable?) {
                    Log.d("onerror", e.toString())
                }
                override fun onComplete() {}
                override fun onNext(value: NoteModel?) {}
            })
    }

}