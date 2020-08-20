package com.example.noteapplication.features.notes.data.repositories

import com.example.noteapplication.features.notes.data.datasources.interfaces.INoteLocalDataSource
import com.example.noteapplication.features.notes.data.datasources.interfaces.INoteRemoteDataSource
import com.example.noteapplication.features.notes.data.mappers.NoteMapper
import com.example.noteapplication.features.notes.domain.models.NoteModel
import com.example.noteapplication.features.notes.domain.repositories.NoteRepository
import com.example.noteapplication.shared.data.GeneralErrorHandlerImpl
import com.example.noteapplication.shared.domain.ResultOf
import com.example.noteapplication.shared.util.EditNoteParams
import com.example.noteapplication.shared.util.NoteNetworkInfo
import retrofit2.Call
import javax.inject.Inject

//https://proandroiddev.com/android-error-handling-in-clean-architecture-844a7fc0dc03
class NoteRepositoryImpl @Inject constructor(
    private val remoteDataSource: INoteRemoteDataSource,
    private val localDataSource: INoteLocalDataSource,
    private val networkInfo: NoteNetworkInfo
): NoteRepository {

    private val noteMapper = NoteMapper()

    override fun getNotes(): ResultOf<List<NoteModel>> {
        lateinit var resultOf: ResultOf<List<NoteModel>>
        return when(networkInfo.isConnected().get()) {
            true -> {
                remoteDataSource.getNotes()
                    .doOnSuccess {
                        localDataSource.cacheNotes(it)
                        val noteList = ArrayList<NoteModel>()
                        for (model in it) {
                            noteList.add(noteMapper.toNoteDetails(model))
                        }
                        resultOf = ResultOf.Success(noteList)
                    }
                    .doOnError {
                        resultOf = ResultOf.Failure(GeneralErrorHandlerImpl.getError(it))
                    }
                resultOf
            }
            false -> {
                val localNotes = localDataSource.getNotes().blockingGet()
                val noteList = ArrayList<NoteModel>()
                for (model in localNotes) {
                    noteList.add(noteMapper.toNoteDetails(model))
                }
                resultOf = ResultOf.Success(noteList)
                resultOf
            }
        }
    }

    override fun deleteNote(noteId: Int): Call<Void> {
        TODO("Not yet implemented")
    }

    override fun postNote(note: NoteModel): Call<Void> {
        TODO("Not yet implemented")
    }

    override fun editNote(params: EditNoteParams): Call<Void>? {
        TODO("Not yet implemented")
    }
    /**
    private val noteMapper = NoteMapper()

    override fun getNotes(): Single<List<NoteModel>> {
        return noteService.getNotes()
            .map {
                val noteList = ArrayList<NoteModel>()
                for (model in it) {
                    noteList.add(noteMapper.toNoteDetails(model))
                }
                noteList
            }
    }

    override fun deleteNote(noteId: Int): Call<Void> {
        return noteService.deleteNote(noteId)
    }

    override fun postNote(note: NoteModel): Call<Void> {
        val submitNote = noteMapper.toPostNote(note)
        return noteService.postNote(submitNote)
    }

    override fun editNote(params: EditNoteParams): Call<Void>? {
        params.note.id?.let {
            val submitNote = noteMapper.toPostNote(params.note)
            return noteService.editNote(it, submitNote)
        }
        return null
    } **/
}