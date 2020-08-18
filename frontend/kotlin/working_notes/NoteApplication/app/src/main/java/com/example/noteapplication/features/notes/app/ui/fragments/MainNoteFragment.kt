package com.example.noteapplication.features.notes.app.ui.fragments

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.noteapplication.R
import com.example.noteapplication.databinding.MainNoteFragmentBinding
import com.example.noteapplication.features.notes.app.ui.NoteAdapter
import com.example.noteapplication.features.notes.app.ui.NoteClickListener
import com.example.noteapplication.features.notes.app.viewmodel.NoteViewModel
import com.example.noteapplication.shared.app.activities.MainActivity
import com.example.noteapplication.shared.util.SessionManager
import javax.inject.Inject


class MainNoteFragment: Fragment() {

    @Inject
    lateinit var noteViewModel: NoteViewModel

    @Inject
    lateinit var sessionManager: SessionManager

    lateinit var binding: MainNoteFragmentBinding

    private val openListener = NoteClickListener {
            noteId ->
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
        binding = DataBindingUtil.inflate(inflater, R.layout.main_note_fragment, container, false )
        binding.getToken.setOnClickListener {
            sessionManager.fetchAuthToken()?.let {
                val alert =
                    AlertDialog.Builder(this@MainNoteFragment.context)
                alert.setTitle("TOKEN")
                alert.setMessage(it)
                alert.setPositiveButton(
                    "Ok"
                ) { dialog, which -> dialog.cancel() }
                alert.show()
            }
        }
        binding.navigateToOther.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.secondaryFragment)
        }
        binding.goToLogin.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.loginFragment)
        }
        noteViewModel.getNotes().value?.let {
            binding.recyclerView.adapter = NoteAdapter(it, openListener, deleteListener)
        }
        binding.getNotes.setOnClickListener {
            noteViewModel.loadNotes()
        }
        binding.addNote.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.submitNoteFragment)
        }
        binding.bindingTest = "hello!"
        return binding.root
    }
}
