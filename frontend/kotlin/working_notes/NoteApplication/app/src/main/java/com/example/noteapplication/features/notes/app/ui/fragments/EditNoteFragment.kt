package com.example.noteapplication.features.notes.app.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.noteapplication.R
import com.example.noteapplication.databinding.EditNoteFragmentBinding

class EditNoteFragment: Fragment() {

    private lateinit var binding: EditNoteFragmentBinding
    //private val args: EditNoteFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.edit_note_fragment, container, false)
        //binding.note = args.noteModel
        return binding.root
    }
}