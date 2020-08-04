package com.example.noteapplication.features.notes.data.services

import com.example.noteapplication.features.notes.data.models.NoteDataModel
import com.example.noteapplication.features.notes.data.models.PostNote
import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface NoteService {

    @GET("/notes")
    fun getNotes(): Single<List<NoteDataModel>>
    @DELETE("/notes/{noteId}")
    fun deleteNote( @Path ("noteId") noteId: Int): Call<Void>
    @POST("/notes")
    fun postNote(@Body note: PostNote): Call<Void>
    @PUT("/notes/{noteId}")
    fun editNote( @Path ("noteId") noteId: Int, @Body note: PostNote): Call<Void>
}