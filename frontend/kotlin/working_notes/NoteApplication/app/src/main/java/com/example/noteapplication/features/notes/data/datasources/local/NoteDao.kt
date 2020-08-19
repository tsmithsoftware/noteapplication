package com.example.noteapplication.features.notes.data.datasources.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.noteapplication.features.notes.data.models.NoteDataModel
import com.example.noteapplication.features.notes.data.models.PostNote
import io.reactivex.Single
import retrofit2.Call

@Dao
interface NoteDao {
    @Query("SELECT * from notes")
    fun getNotes(): List<NoteDataModel>

    @Query("DELETE FROM notes WHERE noteId = :deletedNoteId")
    suspend fun deleteNote(deletedNoteId: Int)


    fun editNote(noteId: Int, note: PostNote): Boolean {
        TODO("Not yet implemented")
    }

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun postNote(note: NoteDataModel)

}