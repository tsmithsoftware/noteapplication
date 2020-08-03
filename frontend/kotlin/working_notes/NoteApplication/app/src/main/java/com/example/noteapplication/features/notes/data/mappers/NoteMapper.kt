package com.example.noteapplication.features.notes.data.mappers

import com.example.noteapplication.features.notes.data.models.NoteDataModel
import com.example.noteapplication.features.notes.data.models.PostNote
import com.example.noteapplication.features.notes.domain.models.NoteModel
import javax.inject.Inject

/**
 * A mapper to map the NoteDataModel from server to NoteModel in a presentable form and vice versa.
 */

class NoteMapper @Inject constructor(){
    fun toNoteDetails(noteDataModel: NoteDataModel): NoteModel {
        return NoteModel(
            id = noteDataModel.noteId,
            noteTitle = noteDataModel.noteTitle,
            noteDetails = noteDataModel.noteDetails
        )
    }

    fun toNote(note: NoteModel): NoteDataModel {
        return NoteDataModel(
            noteId = note.id,
            noteTitle = note.noteTitle,
            noteDetails = note.noteDetails
        )
    }

    fun toPostNote(note: NoteModel): PostNote {
        return PostNote(
            noteTitle = note.noteTitle,
            noteDetails = note.noteDetails
        )
    }
}