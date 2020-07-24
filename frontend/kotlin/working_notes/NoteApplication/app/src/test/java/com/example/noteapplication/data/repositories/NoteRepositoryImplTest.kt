package com.example.noteapplication.data.repositories

import com.example.noteapplication.data.models.NoteDataModel
import com.example.noteapplication.data.services.NoteService
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

class NoteRepositoryImplTest {
    lateinit var sut: NoteRepositoryImpl
    private val mockNoteService: NoteService = mock()
    private val mockNotes: Single<List<NoteDataModel>> = mock()

    @Before
    fun setUp() {
        sut = NoteRepositoryImpl(mockNoteService)
        whenever(mockNoteService.getNotes()).thenReturn(mockNotes)
    }

    @Test
    fun testGetNotesCallsServiceCorrectlyToGetNotes() {
        sut.getNotes()
        verify(mockNoteService.getNotes())
    }
}