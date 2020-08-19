package com.example.noteapplication.features.notes.data.datasources.local

import com.example.noteapplication.features.notes.data.datasources.interfaces.INoteLocalDataSource
import com.example.noteapplication.features.notes.data.models.NoteDataModel
import com.example.noteapplication.features.notes.data.models.PostNote
import io.reactivex.Single
import retrofit2.Call
import javax.inject.Inject

class NoteLocalDataSourceImpl @Inject constructor(private val noteDao: NoteDao): INoteLocalDataSource {

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

    override fun cacheNotes(noteModel: List<NoteDataModel>) {
        TODO("Not yet implemented")
    }
}