package com.example.noteapplication.shared.di.modules

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.noteapplication.features.notes.data.datasources.local.NoteDao
import com.example.noteapplication.features.notes.data.datasources.local.NoteRoomDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class RoomModule(val context: Context) {

    private val noteRoomDatabase = NoteRoomDatabase.getDatabase(context)

    @Singleton
    @Provides
    fun providesRoomDatabase(): NoteRoomDatabase {
        return noteRoomDatabase
    }

    @Singleton
    @Provides
    fun providesNoteDao(roomDatabase: NoteRoomDatabase): NoteDao {
        return roomDatabase.noteDao()
    }
}