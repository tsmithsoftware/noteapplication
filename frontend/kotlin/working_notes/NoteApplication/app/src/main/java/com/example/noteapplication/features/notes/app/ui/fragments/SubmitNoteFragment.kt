package com.example.noteapplication.features.notes.app.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.example.noteapplication.R
import com.example.noteapplication.databinding.AddNoteFragmentBinding
import com.example.noteapplication.shared.app.activities.MainActivity
import com.example.noteapplication.features.notes.app.viewmodel.SubmitNoteViewModel
import com.example.noteapplication.features.notes.domain.models.NoteModel
import javax.inject.Inject


class SubmitNoteFragment: DialogFragment(){

    lateinit var binding: AddNoteFragmentBinding

    @Inject
    lateinit var submitNoteViewModel: SubmitNoteViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as MainActivity).notesComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.add_note_fragment, container, false)
        binding.submit.setOnClickListener {
            val noteTitle = binding.titleValue.text.toString()
            val noteDetails = binding.detailValue.text.toString()
            submitNoteViewModel.submitNote(NoteModel(noteDetails = noteDetails, noteTitle = noteTitle))
            this.dismiss()
        }
        return binding.root
    }
}