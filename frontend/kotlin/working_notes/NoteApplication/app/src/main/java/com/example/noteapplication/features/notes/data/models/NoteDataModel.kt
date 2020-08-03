package com.example.noteapplication.features.notes.data.models

import com.google.gson.annotations.SerializedName

data class NoteDataModel (
    @SerializedName("noteid")
    val noteId: Int? = null,
    @SerializedName("notetitle")
    val noteTitle: String? = null,
    @SerializedName("notedetails")
    val noteDetails: String? = null
)

data class PostNote (
    @SerializedName("noteTitle")
    val noteTitle: String? = null,
    @SerializedName("noteDetails")
    val noteDetails: String? = null
)