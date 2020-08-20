package com.example.noteapplication.features.notes.data.repositories

import androidx.databinding.ObservableBoolean
import com.example.OfflineTests
import com.example.OnlineTests
import com.example.noteapplication.features.notes.data.datasources.interfaces.INoteLocalDataSource
import com.example.noteapplication.features.notes.data.datasources.interfaces.INoteRemoteDataSource
import com.example.noteapplication.features.notes.data.mappers.NoteMapper
import com.example.noteapplication.features.notes.data.models.NoteDataModel
import com.example.noteapplication.features.notes.domain.models.NoteModel
import com.example.noteapplication.shared.util.NoteNetworkInfo
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.experimental.categories.Categories
import org.junit.experimental.categories.Category
import org.junit.runner.RunWith
import org.junit.runners.Suite


class NoteRepositoryImplTest {
    private lateinit var sut: NoteRepositoryImpl
    private val mockLocalDataSource: INoteLocalDataSource = mock()
    private val mockRemoteDataSource: INoteRemoteDataSource = mock()
    private val mockNetworkInfo: NoteNetworkInfo = mock()
    private val stubNotes = listOf(NoteDataModel(noteId = 1, noteTitle = "title", noteDetails = "details"))
    private val stubNotesSingle: Single<List<NoteDataModel>> = Single.just(stubNotes)
    private val note = NoteModel(
        id = 1,
        noteTitle = "Title",
        noteDetails = "Details"
    )
    private val noteDataModel = NoteMapper().toPostNote(note)

    @Before
    fun setUp() {
        sut = NoteRepositoryImpl(
            remoteDataSource = mockRemoteDataSource,
            localDataSource = mockLocalDataSource,
            networkInfo = mockNetworkInfo
        )
        whenever(mockRemoteDataSource.getNotes()).thenReturn(stubNotesSingle)
    }

    @Test
    @Category(OnlineTests::class)
    fun shouldCheckIfDeviceIsOnlineByCallingIsConnected() {
        val mockObservableBoolean: ObservableBoolean = mock()
        whenever(mockObservableBoolean.get()).thenReturn(true)
        whenever(mockNetworkInfo.isConnected()).thenReturn(mockObservableBoolean)
        sut.getNotes()
        verify(mockNetworkInfo).isConnected()
    }

    @Test
    @Category(OnlineTests::class)
    fun shouldReturnRemoteDataWhenRemoteDataCallIsSuccessful() {
        val mockObservableBoolean: ObservableBoolean = mock()
        whenever(mockObservableBoolean.get()).thenReturn(true)
        whenever(mockNetworkInfo.isConnected()).thenReturn(mockObservableBoolean)
        val notes: List<NoteModel> = sut.getNotes().blockingGet()
        val mapper = NoteMapper()
        val mapped = mapper.toNote(notes[0])
        val expectedNote = NoteDataModel(noteId = 1, noteTitle = "title", noteDetails = "details")
        assert(mapped == expectedNote)
    }

    @Test
    @Category(OnlineTests::class)
    fun shouldCacheTheDataLocallyWhenCallToRemoteDataSourceIsSuccessful() {
        val mockObservableBoolean: ObservableBoolean = mock()
        whenever(mockObservableBoolean.get()).thenReturn(true)
        whenever(mockNetworkInfo.isConnected()).thenReturn(mockObservableBoolean)
        sut.getNotes()
        verify(mockLocalDataSource).cacheNotes(stubNotes)
    }

    @Test
    @Category(OfflineTests::class)
    fun shouldReturnLastLocallyCachedDataWhenCachedDataIsPresent() {
        val mockObservableBoolean: ObservableBoolean = mock()
        whenever(mockObservableBoolean.get()).thenReturn(false)
        whenever(mockNetworkInfo.isConnected()).thenReturn(mockObservableBoolean)
        whenever(mockLocalDataSource.getNotes()).thenReturn(stubNotesSingle)
        val notes: List<NoteModel> = sut.getNotes().blockingGet()
        val mapper = NoteMapper()
        val mapped = mapper.toNote(notes[0])
        val expectedNote = NoteDataModel(noteId = 1, noteTitle = "title", noteDetails = "details")
        assert(mapped == expectedNote)
        verify(mockLocalDataSource).getNotes()
        verifyNoMoreInteractions(mockRemoteDataSource)
    }
}


@RunWith(Categories::class)
@Categories.IncludeCategory(
    OnlineTests::class
)
@Suite.SuiteClasses(NoteRepositoryImplTest::class)
class NoteRepositoryOnlineTestSuite

@RunWith(Categories::class)
@Categories.IncludeCategory(
    OfflineTests::class
)
@Suite.SuiteClasses(NoteRepositoryImplTest::class)
class NoteRepositoryOfflineTestSuite