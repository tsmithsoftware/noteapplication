package com.example.noteapplication.features.notes.domain.usecases

import com.example.noteapplication.features.notes.domain.models.NoteModel
import com.example.noteapplication.features.notes.domain.repositories.NoteRepository
import com.example.noteapplication.shared.util.NoParams
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import junit.framework.Assert.fail
import org.junit.Test

class GetNotesUseCaseTest {

    private val mockNotesRepository: NoteRepository = mock()
    private val mockSingle: Single<List<NoteModel>> = mock()

    @Test
    fun testUseCaseCallsRepoGetNotesMethod() {
        /**whenever(mockNotesRepository.getNotes()).thenReturn(mockSingle)
        val sut = GetNotesUseCase(mockNotesRepository)
        val result = sut.execute(NoParams())
        verify(mockNotesRepository).getNotes()
        assert(result == mockSingle)**/
        fail("not implemented")
    }

    @Test
    fun testUseCaseReturnsErrorsCorrectly() {
        whenever(mockNotesRepository.getNotes()).thenThrow()
        val sut = GetNotesUseCase(mockNotesRepository)
        val result = sut.execute(NoParams())
        verify(mockNotesRepository).getNotes()
        assert(result == mockSingle)
    }
}