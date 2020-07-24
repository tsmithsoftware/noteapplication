package com.example.noteapplication.data.repositories

import com.example.noteapplication.data.mappers.NoteMapper
import com.example.noteapplication.data.services.NoteService
import com.example.noteapplication.domain.models.NoteModel
import com.example.noteapplication.domain.repositories.NoteRepository
import io.reactivex.Single
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
}