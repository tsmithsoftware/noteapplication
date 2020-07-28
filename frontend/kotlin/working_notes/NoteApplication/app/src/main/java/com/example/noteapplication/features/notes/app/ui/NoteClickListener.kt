package com.example.noteapplication.features.notes.app.ui

import com.example.noteapplication.features.notes.domain.models.NoteModel

class NoteClickListener(val clickListener: (noteId: Int?) -> Unit) {
    fun onClick(note: NoteModel) = clickListener(note.id)
}