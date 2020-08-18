package com.example.noteapplication.features.notes.data.mappers

import com.example.noteapplication.features.notes.data.models.NoteDataModel
import com.example.noteapplication.features.notes.data.mappers.NoteMapper
import com.example.noteapplication.features.notes.domain.models.NoteModel
import org.junit.Test

class NoteMapperTest {

    @Test
    fun testNoteMapperCorrectlyMapsNoteDataModelToNoteModel() {
        val noteDataModel = NoteDataModel(
            noteId = 1,
            noteTitle = "Note Title",
            noteDetails = "Note"
        )
        val result = NoteMapper().toNoteDetails(noteDataModel)
        assert(result.id == 1)
        assert(result.noteTitle == "Note Title")
        assert(result.noteDetails == "Note")
    }

    @Test
    fun testNoteMapperCorrectlyMapsNoteModelToNoteDataModel() {
        val noteModel = NoteModel(
            id = 1,
            noteTitle = "NoteTitle",
            noteDetails = "Note"
        )

        val result = NoteMapper().toNote(noteModel)
        assert(result.noteId == 1)
        assert(result.noteTitle == "NoteTitle")
        assert(result.noteDetails == "Note")
    }
}