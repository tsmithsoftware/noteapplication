package com.timsmith.notesapplication.app.di.modules

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.timsmith.notesapplication.R
import com.timsmith.notesapplication.data.repositories.NoteRepositoryImpl
import com.timsmith.notesapplication.data.services.NoteService
import com.timsmith.notesapplication.domain.repositories.NoteRepository
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val okHttpBuilder = OkHttpClient.Builder()
        okHttpBuilder.addInterceptor(HttpLoggingInterceptor())
        return okHttpBuilder.build()
    }

    @Singleton
    @Provides
    fun provideGson(): Gson {
        return GsonBuilder()
            .setLenient()
            .create()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        //.baseUrl(context.getResources().getString(R.string.base_url))
        return Retrofit.Builder()
            .baseUrl("http://localhost:4000")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()
    }
}