package com.timsmith.notesapplication.app.viewmodel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.timsmith.notesapplication.app.rx.SchedulersProvider
import com.timsmith.notesapplication.domain.models.NoteModel
import com.timsmith.notesapplication.domain.usecases.GetNotesUseCase
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class MainViewModel @Inject constructor(
    val shareUseCase: GetNotesUseCase,
    val schedulers: SchedulersProvider
) : ViewModel() {

    val shareLiveData = MutableLiveData<List<NoteModel>>()
    protected val compositeDisposable = CompositeDisposable()

    fun getShareData() {
        shareUseCase.execute()
            .subscribeOn(schedulers.io())
            .subscribe({
                it?.let {
                    shareLiveData.postValue(it)
                }
            },{

            }).let {
                compositeDisposable.add(it)
            }
    }

    override fun onCleared() {
        compositeDisposable.clear()
    }

}