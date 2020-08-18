package com.example.noteapplication.shared.di.modules

import android.content.Context
import com.example.noteapplication.features.login.data.repositories.LoginRepositoryImpl
import com.example.noteapplication.features.login.data.services.LoginService
import com.example.noteapplication.features.login.domain.models.LoginResponse
import com.example.noteapplication.features.login.domain.repositories.LoginRepository
import com.example.noteapplication.features.notes.data.repositories.NoteRepositoryImpl
import com.example.noteapplication.features.notes.data.services.NoteService
import com.example.noteapplication.features.notes.domain.repositories.NoteRepository
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
    fun provideLoginService(retrofit: Retrofit): LoginService {
        return retrofit.create(LoginService::class.java)
    }

    @Provides
    fun provideNoteRepository(noteService: NoteService): NoteRepository {
        return NoteRepositoryImpl(noteService)
    }

    @Provides
    fun provideLoginRepository(loginService: LoginService): LoginRepository {
        return LoginRepositoryImpl(loginService)
    }

}