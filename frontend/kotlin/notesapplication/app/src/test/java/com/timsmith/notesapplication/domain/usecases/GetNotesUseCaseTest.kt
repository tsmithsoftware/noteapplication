package com.timsmith.notesapplication.domain.usecases

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import com.timsmith.notesapplication.domain.models.NoteModel
import com.timsmith.notesapplication.domain.repositories.NoteRepository
import io.reactivex.Single
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class GetNotesUseCaseTest {

    private val mockNotesRepository: NoteRepository = mock()
    private val mockSingle: Single<List<NoteModel>> = mock()

    @Before
    fun setup() {
        whenever(mockNotesRepository.getNotes()).thenReturn(mockSingle)
    }

    @Test
    fun testUseCaseCallsRepoGetNotesMethod() {
        val sut = GetNotesUseCase(mockNotesRepository)
        val result = sut.execute()
        verify(mockNotesRepository.getNotes())
        assert(result == mockSingle)
    }
}