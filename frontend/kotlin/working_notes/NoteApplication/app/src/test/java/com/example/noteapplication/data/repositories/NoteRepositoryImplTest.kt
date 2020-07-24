package com.example.noteapplication.data.repositories

import com.example.noteapplication.app.di.modules.NetworkModule
import com.example.noteapplication.data.models.NoteDataModel
import com.example.noteapplication.data.services.NoteService
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

class NoteRepositoryImplTest {
    lateinit var sut: NoteRepositoryImpl
    private val mockNoteService: NoteService = com.nhaarman.mockito_kotlin.mock()
    private val mockNotes: Single<List<NoteDataModel>> = com.nhaarman.mockito_kotlin.mock()

    @Before
    fun setUp() {
        sut = NoteRepositoryImpl(mockNoteService)
        whenever(mockNoteService.getNotes()).thenReturn(mockNotes)
    }

    @Test
    fun testGetNotesCallsServiceCorrectlyToGetNotes() {
        sut.getNotes()
        com.nhaarman.mockito_kotlin.verify(mockNoteService.getNotes())
    }
}