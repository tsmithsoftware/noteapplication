package com.example.noteapplication.shared.app.activities

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import com.example.noteapplication.NoteApplication
import com.example.noteapplication.R
import com.example.noteapplication.shared.di.components.NotesComponent
import com.example.noteapplication.databinding.ActivityMainBinding

class MainActivity: FragmentActivity() {
    // Reference to the Login graph
    lateinit var notesComponent: NotesComponent
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        // Creation of the login graph using the application graph
        notesComponent = (applicationContext as NoteApplication).appComponent.notesComponent().create()

        // Make Dagger instantiate @Inject fields in LoginActivity
        notesComponent.inject(this)
        setContentView(binding.root)
    }

}
