package com.example.noteapplication.app.ui

import android.app.Activity
import android.os.Bundle
import android.util.Log
import com.example.noteapplication.NoteApplication
import com.example.noteapplication.app.viewmodel.NoteViewModel
import com.example.noteapplication.app.di.components.NotesComponent
import javax.inject.Inject

class MainActivity: Activity() {
    // Reference to the Login graph
    lateinit var notesComponent: NotesComponent

    // Fields that need to be injected by the login graph
    @Inject lateinit var loginViewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        // Creation of the login graph using the application graph
        notesComponent = (applicationContext as NoteApplication)
            .appComponent.notesComponent().create()

        // Make Dagger instantiate @Inject fields in LoginActivity
        notesComponent.inject(this)

        // Now loginViewModel is available
        Log.d("Accessing vm:", loginViewModel.stringValue)

        super.onCreate(savedInstanceState)
    }

}
