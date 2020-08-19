package com.example.noteapplication.features.notes.data.datasources.remote

import com.example.noteapplication.features.notes.data.datasources.interfaces.INoteRemoteDataSource
import com.example.noteapplication.features.notes.data.models.NoteDataModel
import com.example.noteapplication.features.notes.data.models.PostNote
import io.reactivex.Single
import retrofit2.Call
import javax.inject.Inject

class NoteRemoteDataSourceImpl @Inject constructor(private val notesService: NoteService): INoteRemoteDataSource {

    override fun getNotes(): Single<List<NoteDataModel>> {
        TODO("Not yet implemented")
    }

    override fun deleteNote(noteId: Int): Call<Void> {
        TODO("Not yet implemented")
    }

    override fun editNote(noteId: Int, note: PostNote): Call<Void> {
        TODO("Not yet implemented")
    }

    override fun postNote(note: PostNote): Call<Void> {
        TODO("Not yet implemented")
    }
}