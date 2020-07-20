package com.example.noteapplication

import android.app.Application
import com.example.noteapplication.app.di.components.ApplicationComponent
import com.example.noteapplication.app.di.components.DaggerApplicationComponent

// appComponent lives in the Application class to share its lifecycle
class NoteApplication: Application() {
    // Reference to the application graph that is used across the whole app
    val appComponent: ApplicationComponent = DaggerApplicationComponent.create()
}
