package com.example.noteapplication.data.models

import com.google.gson.annotations.SerializedName

data class NoteDataModel (
    @SerializedName("noteid")
    val noteId: Int = -1,
    @SerializedName("notedetails")
    val noteDetails: String? = null
)