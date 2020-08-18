package com.example.noteapplication.features.notes.domain.usecases

import com.example.noteapplication.features.notes.domain.repositories.NoteRepository
import com.example.noteapplication.shared.util.DeleteNoteParams
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import org.junit.Before
import org.junit.Test
import retrofit2.Call

class DeleteNoteUseCaseTest {

    private val mockNotesRepository: NoteRepository = mock()
    private val mockSingle: Call<Void> = mock()
    private val params: DeleteNoteParams =
        DeleteNoteParams(id = 1)

    @Before
    fun setup() {
        whenever(mockNotesRepository.deleteNote(any())).thenReturn(mockSingle)
    }

    @Test
    fun testUseCaseCallsRepoGetNotesMethod() {
        val sut = DeleteNoteUseCase(mockNotesRepository)
        sut.execute(params)
        verify(mockNotesRepository).deleteNote(1)
    }
}