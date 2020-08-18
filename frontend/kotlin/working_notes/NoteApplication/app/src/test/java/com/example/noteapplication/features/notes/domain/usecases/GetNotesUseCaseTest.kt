package com.example.noteapplication.features.notes.domain.usecases

import com.example.noteapplication.features.notes.domain.models.NoteModel
import com.example.noteapplication.features.notes.domain.repositories.NoteRepository
import com.example.noteapplication.shared.util.NoParams
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
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
        val result = sut.execute(NoParams())
        verify(mockNotesRepository).getNotes()
        assert(result == mockSingle)
    }
}