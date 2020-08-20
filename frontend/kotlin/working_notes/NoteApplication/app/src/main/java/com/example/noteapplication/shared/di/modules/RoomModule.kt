package com.example.noteapplication.shared.di.modules

import android.content.Context
import com.example.noteapplication.features.notes.data.datasources.local.NoteDao
import com.example.noteapplication.features.notes.data.datasources.local.NoteRoomDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class RoomModule() {

    @Singleton
    @Provides
    fun providesRoomDatabase(context: Context): NoteRoomDatabase {
        return NoteRoomDatabase.getDatabase(context)
    }

    @Singleton
    @Provides
    fun providesNoteDao(roomDatabase: NoteRoomDatabase): NoteDao {
        return roomDatabase.noteDao()
    }
}