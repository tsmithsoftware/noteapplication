package com.example.noteapplication.features.notes.domain.usecases

import com.example.noteapplication.features.notes.domain.models.NoteModel
import com.example.noteapplication.features.notes.domain.repositories.NoteRepository
import com.example.noteapplication.shared.util.EditNoteParams
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import org.junit.Before
import org.junit.Test
import retrofit2.Call

class EditNoteUseCaseTest {
    private val mockNotesRepository: NoteRepository = mock()
    private val mockSingle: Call<Void> = mock()
    private var params: EditNoteParams
    private val note = NoteModel()

    init {
        params = EditNoteParams(note)
    }

    @Before
    fun setup() {
        whenever(mockNotesRepository.editNote(params)).thenReturn(mockSingle)
    }

    @Test
    fun testUseCaseCallsRepoGetNotesMethod() {
        val sut = EditNoteUseCase(mockNotesRepository)
        sut.execute(params)
        verify(mockNotesRepository).editNote(params)
    }
}