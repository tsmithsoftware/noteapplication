package com.timsmith.notesapplication.data.repositories

import com.timsmith.notesapplication.data.mappers.NoteMapper
import com.timsmith.notesapplication.data.models.NoteDataModel
import com.timsmith.notesapplication.data.services.NoteService
import com.timsmith.notesapplication.domain.models.NoteModel
import com.timsmith.notesapplication.domain.repositories.NoteRepository
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