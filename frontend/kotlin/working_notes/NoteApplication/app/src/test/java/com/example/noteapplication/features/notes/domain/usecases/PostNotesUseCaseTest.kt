package com.example.noteapplication.features.notes.domain.usecases

import com.example.noteapplication.features.notes.data.models.NoteDataModel
import com.example.noteapplication.features.notes.domain.models.NoteModel
import com.example.noteapplication.features.notes.domain.repositories.NoteRepository
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import org.junit.Before
import org.junit.Test
import retrofit2.Call

class PostNotesUseCaseTest {

    private val mockNotesRepository: NoteRepository = mock()
    private val mockCall: Call<Void> = mock()
    private val note = NoteModel()

    @Before
    fun setup() {
        whenever(mockNotesRepository.postNote(note)).thenReturn(mockCall)
    }

    @Test
    fun testUseCaseCallsRepoGetNotesMethod() {
        val sut = PostNotesUseCase(mockNotesRepository)
        sut.execute(AddNoteParams(note))
        verify(mockNotesRepository).postNote(note)
    }
}