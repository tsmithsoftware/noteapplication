package com.example.noteapplication.features.notes.data.mappers

import com.example.noteapplication.features.notes.data.models.NoteDataModel
import com.example.noteapplication.features.notes.data.mappers.NoteMapper
import org.junit.Test

class NoteMapperTest {

    @Test
    fun testNoteMapperCorrectlyMapsNoteDataModelToNodeModel() {
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
}