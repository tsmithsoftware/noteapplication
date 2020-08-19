package com.example.noteapplication.features.notes.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "notes")
data class NoteDataModel (
    @SerializedName("noteid")
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "noteId")
    val noteId: Int? = null,
    @SerializedName("notetitle")
    @ColumnInfo(name = "noteTitle")
    val noteTitle: String? = null,
    @SerializedName("notedetails")
    @ColumnInfo(name = "noteDetails")
    val noteDetails: String? = null
)

data class PostNote (
    @SerializedName("noteTitle")
    val noteTitle: String? = null,
    @SerializedName("noteDetails")
    val noteDetails: String? = null
)