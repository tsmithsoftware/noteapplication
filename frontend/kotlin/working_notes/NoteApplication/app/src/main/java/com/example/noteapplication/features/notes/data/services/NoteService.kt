package com.example.noteapplication.features.notes.data.services

import com.example.noteapplication.features.notes.data.models.NoteDataModel
import io.reactivex.Single
import retrofit2.http.GET

interface NoteService {

    @GET("/notes")
    fun getNotes(): Single<List<NoteDataModel>>
}