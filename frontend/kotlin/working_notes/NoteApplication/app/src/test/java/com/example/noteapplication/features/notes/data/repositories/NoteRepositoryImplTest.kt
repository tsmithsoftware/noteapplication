package com.example.noteapplication.features.notes.data.repositories

import com.example.noteapplication.features.notes.data.mappers.NoteMapper
import com.example.noteapplication.features.notes.data.models.NoteDataModel
import com.example.noteapplication.features.notes.data.services.NoteService
import com.example.noteapplication.features.notes.domain.models.NoteModel
import com.example.noteapplication.features.notes.domain.usecases.EditNoteParams
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

class NoteRepositoryImplTest {
    private lateinit var sut: NoteRepositoryImpl
    private val mockNoteService: NoteService = mock()
    private val mockNotes: Single<List<NoteDataModel>> = mock()
    private val note = NoteModel(
        id = 1,
        noteTitle = "Title",
        noteDetails = "Details"
    )
    private val noteDataModel = NoteMapper().toPostNote(note)

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

    @Test
    fun testPostNoteCallsServiceCorrectlyToPostNote() {
        sut.postNote(note)
        verify(mockNoteService).postNote(noteDataModel)
    }

    @Test
    fun testEditNoteCallsServiceCorrectlyToEditNote() {
        val params = EditNoteParams(note)
        sut.editNote(params)
        verify(mockNoteService).editNote(1, noteDataModel)
    }
}