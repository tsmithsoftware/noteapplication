package com.example.noteapplication.features.notes.data.models

import com.google.gson.annotations.SerializedName

data class NoteDataModel (
    @SerializedName("noteid")
    val noteId: Int = -1,
    @SerializedName("notetitle")
    val noteTitle: String? = null,
    @SerializedName("notedetails")
    val noteDetails: String? = null
)