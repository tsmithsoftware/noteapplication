package com.example.noteapplication.features.notes.data.repositories

import com.example.noteapplication.features.notes.data.mappers.NoteMapper
import com.example.noteapplication.features.notes.data.services.NoteService
import com.example.noteapplication.features.notes.domain.models.NoteModel
import com.example.noteapplication.features.notes.domain.repositories.NoteRepository
import io.reactivex.Single
import retrofit2.Call
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val noteService: NoteService
): NoteRepository {
    private val noteMapper = NoteMapper()

    override fun getNotes(): Single<List<NoteModel>> {
        return noteService.getNotes()
            .map {
                val noteList = ArrayList<NoteModel>()
                for (model in it) {
                    noteList.add(noteMapper.toNoteDetails(model))
                }
                noteList
            }
    }

    override fun deleteNote(noteId: Int): Call<Void> {
        return noteService.deleteNote(noteId)
    }
}