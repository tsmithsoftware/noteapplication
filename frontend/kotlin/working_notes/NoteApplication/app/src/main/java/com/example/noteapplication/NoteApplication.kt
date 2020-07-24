package com.example.noteapplication

import android.app.Application
import com.example.noteapplication.app.di.components.ApplicationComponent
import com.example.noteapplication.app.di.components.DaggerApplicationComponent
import com.example.noteapplication.app.di.modules.NoteModule
import com.example.noteapplication.data.mappers.NoteMapper_Factory.create

// appComponent lives in the Application class to share its lifecycle
class NoteApplication: Application() {
    // Reference to the application graph that is used across the whole app
    val appComponent: ApplicationComponent = DaggerApplicationComponent
        .builder()
        .noteModule(NoteModule(this))
        .build()
}
