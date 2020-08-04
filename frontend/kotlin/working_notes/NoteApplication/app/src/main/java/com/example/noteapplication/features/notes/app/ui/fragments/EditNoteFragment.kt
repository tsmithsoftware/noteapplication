package com.example.noteapplication.features.notes.app.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.noteapplication.R
import com.example.noteapplication.databinding.EditNoteFragmentBinding
import com.example.noteapplication.features.notes.app.ui.activities.MainActivity
import com.example.noteapplication.features.notes.app.viewmodel.EditNoteViewModel
import com.example.noteapplication.features.notes.domain.models.NoteModel
import javax.inject.Inject

class EditNoteFragment: Fragment() {

    @Inject
    lateinit var editNoteModel: EditNoteViewModel

    private lateinit var binding: EditNoteFragmentBinding
    private val args: EditNoteFragmentArgs by navArgs()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as MainActivity).notesComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.edit_note_fragment, container, false)
        args.noteModel.let { noteModel ->
            binding.note = noteModel
            binding.submit.setOnClickListener {
                val newTitle = binding.titleText.text.toString()
                val newDetails = binding.detailsText.text.toString()
                if (newTitle.isNotBlank()) noteModel.noteTitle = newTitle
                if (newDetails.isNotBlank()) noteModel.noteDetails = newDetails
                editNoteModel.editNote(noteModel)
                binding.titleText.clearFocus()
                binding.detailsText.clearFocus()
                Navigation.findNavController(it).popBackStack()
            }
        }

        return binding.root
    }
}