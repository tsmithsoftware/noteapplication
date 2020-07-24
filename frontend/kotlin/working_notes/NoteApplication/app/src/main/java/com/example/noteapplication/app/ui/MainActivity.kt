package com.example.noteapplication.app.ui

import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import com.example.noteapplication.NoteApplication
import com.example.noteapplication.R
import com.example.noteapplication.app.viewmodel.NoteViewModel
import com.example.noteapplication.app.di.components.NotesComponent
import com.example.noteapplication.databinding.ActivityMainBinding
import com.example.noteapplication.domain.models.NoteModel
import io.reactivex.Observer
import io.reactivex.observers.DisposableSingleObserver
import javax.inject.Inject

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
