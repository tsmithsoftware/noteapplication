package com.timsmith.notesapplication.data.models
import com.google.gson.annotations.SerializedName

data class NoteDataModel (
    @SerializedName("notesid")
    val noteId: Int = -1,
    @SerializedName("notesid")
    val noteDetails: String? = null
)