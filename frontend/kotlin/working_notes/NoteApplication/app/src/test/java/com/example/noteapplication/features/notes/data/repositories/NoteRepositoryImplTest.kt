package com.example.noteapplication.features.notes.data.repositories

import com.example.noteapplication.features.notes.data.models.NoteDataModel
import com.example.noteapplication.features.notes.data.services.NoteService
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
        verify(mockNoteService).getNotes()
    }

    @Test
    fun testDeleteNoteCallsServiceCorrectlyToDeleteNote() {
        sut.deleteNote(1)
        verify(mockNoteService).deleteNote(1)
    }
}