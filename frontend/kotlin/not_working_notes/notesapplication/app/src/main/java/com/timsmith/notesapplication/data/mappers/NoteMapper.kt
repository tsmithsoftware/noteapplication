package com.timsmith.notesapplication.data.mappers

import com.timsmith.notesapplication.data.models.NoteDataModel
import com.timsmith.notesapplication.domain.models.NoteModel
import javax.inject.Inject

/**
 * A mapper to map the NoteDataModel from server to NoteModel in a presentable form.
 */

class NoteMapper @Inject constructor(){
    fun toNoteDetails(noteDataModel: NoteDataModel): NoteModel {
        return NoteModel(
            id = noteDataModel.noteId,
            noteDetails = noteDataModel.noteDetails
        )
    }
}