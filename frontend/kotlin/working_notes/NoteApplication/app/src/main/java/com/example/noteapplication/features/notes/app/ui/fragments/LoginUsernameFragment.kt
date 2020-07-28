package com.example.noteapplication.features.notes.app.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.noteapplication.R
import com.example.noteapplication.features.notes.app.ui.activities.MainActivity
import com.example.noteapplication.features.notes.app.ui.NoteAdapter
import com.example.noteapplication.features.notes.app.ui.NoteClickListener
import com.example.noteapplication.features.notes.app.viewmodel.NoteViewModel
import com.example.noteapplication.databinding.LoginUsernameFragmentBinding
import javax.inject.Inject

class LoginUsernameFragment: Fragment() {

    @Inject
    lateinit var noteViewModel: NoteViewModel

    lateinit var binding: LoginUsernameFragmentBinding

    private val openListener = NoteClickListener {
            noteId ->
        Toast.makeText(context, "$noteId", Toast.LENGTH_LONG).show()
        val notes = noteViewModel.getNotes().value
        notes?.let { noteCollection ->
            noteId?.let { noteId ->
                val note = noteCollection.find{ it.id == noteId }
                if ( note  != null ) {
                    val action = EditNoteFragmentDirections.goToEditNote(note)
                    Navigation.findNavController(binding.root).navigate(action)
                }
            }
        }
    }

    private val deleteListener = NoteClickListener {
        noteId ->
        noteViewModel.onDeleteNoteClicked(noteId)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as MainActivity).notesComponent.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        noteViewModel.getNotes().observe(this, androidx.lifecycle.Observer { notes ->
            val noteAdapter = NoteAdapter(notes,  openListener, deleteListener)
            binding.recyclerView.adapter =  noteAdapter
            noteAdapter.notifyDataSetChanged()
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.login_username_fragment, container, false )
        binding.navigateToOther.setOnClickListener {
           this.view?.let {
               Navigation.findNavController(it).navigate(R.id.loginPasswordFragment)
           }
        }
        noteViewModel.getNotes().value?.let {
            binding.recyclerView.adapter = NoteAdapter(it, openListener, deleteListener)
        }
        binding.getNotes.setOnClickListener {
            noteViewModel.loadNotes()
        }
        binding.bindingTest = "hello!"
        return binding.root
    }
}
