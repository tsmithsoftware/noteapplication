package com.example.noteapplication.shared.di.modules

import android.content.Context
import com.example.noteapplication.features.login.data.repositories.LoginRepositoryImpl
import com.example.noteapplication.features.login.data.services.LoginService
import com.example.noteapplication.features.login.domain.repositories.LoginRepository
import com.example.noteapplication.features.notes.data.datasources.local.NoteDao
import com.example.noteapplication.features.notes.data.datasources.local.NoteLocalDataSourceImpl
import com.example.noteapplication.features.notes.data.datasources.remote.NoteRemoteDataSourceImpl
import com.example.noteapplication.features.notes.data.datasources.remote.NoteService
import com.example.noteapplication.features.notes.data.repositories.NoteRepositoryImpl
import com.example.noteapplication.features.notes.domain.repositories.NoteRepository
import com.example.noteapplication.shared.util.NoteNetworkInfo
import com.example.noteapplication.shared.util.NoteNetworkInfoImpl
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

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
    fun provideRemoteNoteDataSource(noteService: NoteService): NoteRemoteDataSourceImpl {
        return NoteRemoteDataSourceImpl(noteService)
    }

    @Provides
    fun provideLocalNoteDataSource(noteDao: NoteDao): NoteLocalDataSourceImpl {
        return NoteLocalDataSourceImpl(noteDao)
    }

    @Provides
    @Singleton
    fun provideNoteRepository(
        remoteDataSource: NoteRemoteDataSourceImpl,
        localDataSource: NoteLocalDataSourceImpl,
        networkInfo: NoteNetworkInfo
    ): NoteRepository {
        return NoteRepositoryImpl(
            remoteDataSource = remoteDataSource,
            localDataSource = localDataSource,
            networkInfo = networkInfo
        )
    }

    @Provides
    @Singleton
    fun provideLoginRepository(loginService: LoginService): LoginRepository {
        return LoginRepositoryImpl(loginService)
    }

    @Provides
    @Singleton
    fun provideNetworkInfo(context: Context): NoteNetworkInfo {
        return NoteNetworkInfoImpl(context)
    }

}