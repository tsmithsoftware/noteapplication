package com.timsmith.notesapplication.data.repositories

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import com.timsmith.notesapplication.data.mappers.NoteMapper
import com.timsmith.notesapplication.data.models.NoteDataModel
import com.timsmith.notesapplication.data.services.NoteService
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

class NoteRepositoryImplTest {
    lateinit var sut: NoteRepositoryImpl
    private val mockNoteService: NoteService = mock()
    private val mockNotes: Single<List<NoteDataModel>> = mock()

    @Before
    fun setUp() {
        sut = NoteRepositoryImpl(mockNoteService);
        whenever(mockNoteService.getNotes()).thenReturn(mockNotes)
    }

    @Test
    fun testGetNotesCallsServiceCorrectlyToGetNotes() {
        val result = sut.getNotes()
        verify(mockNoteService.getNotes())
    }
}