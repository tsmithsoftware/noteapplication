package com.example.noteapplication.app.ui

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import com.example.noteapplication.NoteApplication
import com.example.noteapplication.R
import com.example.noteapplication.app.viewmodel.NoteViewModel
import com.example.noteapplication.app.di.components.NotesComponent
import com.example.noteapplication.databinding.ActivityMainBinding
import javax.inject.Inject

class MainActivity: FragmentActivity() {
    // Reference to the Login graph
    lateinit var notesComponent: NotesComponent
    lateinit var binding: ActivityMainBinding

    // Fields that need to be injected by the login graph
    @Inject lateinit var noteViewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        // Creation of the login graph using the application graph
        notesComponent = (applicationContext as NoteApplication)
            .appComponent.notesComponent().create()

        // Make Dagger instantiate @Inject fields in LoginActivity
        notesComponent.inject(this)

        // Now loginViewModel is available
        Log.d("Accessing vm:", noteViewModel.stringValue)

        setContentView(binding.root)
    }

}
