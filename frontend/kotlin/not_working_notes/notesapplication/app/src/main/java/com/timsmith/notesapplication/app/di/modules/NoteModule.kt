package com.timsmith.notesapplication.app.di.modules

import com.timsmith.notesapplication.data.repositories.NoteRepositoryImpl
import com.timsmith.notesapplication.data.services.NoteService
import com.timsmith.notesapplication.domain.repositories.NoteRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module(includes = [NetworkModule::class])
class NoteModule {

    @Provides
    fun provideNoteService(retrofit: Retrofit): NoteService {
        return retrofit.create(NoteService::class.java)
    }

    @Provides
    fun provideNoteRepository(noteService: NoteService): NoteRepository {
        return NoteRepositoryImpl(noteService)
    }

}