package com.example.noteapplication.domain.repositories

import com.example.noteapplication.data.services.NoteService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NoteRepository @Inject constructor(
    private val localDataSource: NoteLocalDataSource,
    private val remoteDataSource: NoteRemoteDataSource
) {  }

class NoteLocalDataSource @Inject constructor() {  }
class NoteRemoteDataSource @Inject constructor(
    private val noteService: NoteService
) {  }
