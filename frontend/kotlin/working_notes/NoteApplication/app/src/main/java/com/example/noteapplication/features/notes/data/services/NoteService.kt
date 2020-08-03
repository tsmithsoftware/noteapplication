package com.example.noteapplication.features.notes.data.services

import com.example.noteapplication.features.notes.data.models.NoteDataModel
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path

interface NoteService {

    @GET("/notes")
    fun getNotes(): Single<List<NoteDataModel>>
    @DELETE("/notes/{noteId}")
    fun deleteNote( @Path ("noteId") noteId: Int): Call<Void>
}