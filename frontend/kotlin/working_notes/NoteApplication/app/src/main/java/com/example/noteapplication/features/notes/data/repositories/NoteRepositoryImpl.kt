package com.example.noteapplication.features.notes.data.repositories

import com.example.noteapplication.features.notes.data.mappers.NoteMapper
import com.example.noteapplication.features.notes.data.models.NoteDataModel
import com.example.noteapplication.features.notes.data.services.NoteService
import com.example.noteapplication.features.notes.domain.models.NoteModel
import com.example.noteapplication.features.notes.domain.repositories.NoteRepository
import com.example.noteapplication.features.notes.domain.usecases.EditNoteParams
import io.reactivex.Single
import okhttp3.ResponseBody
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

    override fun postNote(note: NoteModel): Call<Void> {
        val submitNote = noteMapper.toPostNote(note)
        return noteService.postNote(submitNote)
    }

    override fun editNote(params: EditNoteParams): Call<Void>? {
        params.note.id?.let {
            val submitNote = noteMapper.toPostNote(params.note)
            return noteService.editNote(it, submitNote)
        }
        return null
    }
}