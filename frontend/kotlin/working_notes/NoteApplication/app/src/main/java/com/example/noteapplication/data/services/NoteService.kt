package com.example.noteapplication.data.services

import com.example.noteapplication.data.models.NoteDataModel
import io.reactivex.Single
import retrofit2.http.GET

interface NoteService {

    @GET("/notes")
    fun getNotes(): Single<List<NoteDataModel>>
}