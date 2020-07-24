package com.example.noteapplication.data.mappers

import com.example.noteapplication.data.models.NoteDataModel
import com.example.noteapplication.domain.models.NoteModel
import javax.inject.Inject

/**
 * A mapper to map the NoteDataModel from server to NoteModel in a presentable form.
 */

class NoteMapper @Inject constructor(){
    fun toNoteDetails(noteDataModel: NoteDataModel): NoteModel {
        return NoteModel(
            id = noteDataModel.noteId,
            noteTitle = noteDataModel.noteTitle,
            noteDetails = noteDataModel.noteDetails
        )
    }
}