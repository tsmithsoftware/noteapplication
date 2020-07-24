package com.example.noteapplication.app.di.modules

import android.content.Context
import com.example.noteapplication.data.repositories.NoteRepositoryImpl
import com.example.noteapplication.data.services.NoteService
import com.example.noteapplication.domain.repositories.NoteRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module(includes = [NetworkModule::class])
class NoteModule(val context: Context) {

    @Provides
    fun provideContext(): Context {
        return context
    }

    @Provides
    fun provideNoteService(retrofit: Retrofit): NoteService {
        return retrofit.create(NoteService::class.java)
    }

    @Provides
    fun provideNoteRepository(noteService: NoteService): NoteRepository {
        return NoteRepositoryImpl(noteService)
    }

}