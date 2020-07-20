package com.timsmith.notesapplication.data.services

import com.timsmith.notesapplication.data.models.NoteDataModel
import com.timsmith.notesapplication.domain.models.NoteModel
import io.reactivex.Single
import retrofit2.http.GET

interface NoteService {

    @GET("/notes")
    fun getNotes(): Single<List<NoteDataModel>>
}