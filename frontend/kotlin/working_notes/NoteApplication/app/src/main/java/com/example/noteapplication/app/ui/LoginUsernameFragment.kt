package com.example.noteapplication.app.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.noteapplication.R
import com.example.noteapplication.app.viewmodel.NoteViewModel
import com.example.noteapplication.databinding.LoginUsernameFragmentBinding
import javax.inject.Inject

class LoginUsernameFragment: Fragment() {

    @Inject
    lateinit var noteViewModel: NoteViewModel

    lateinit var binding: LoginUsernameFragmentBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as MainActivity).notesComponent.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        noteViewModel.getNotes().observe(this, androidx.lifecycle.Observer { notes ->
            binding.recyclerView.adapter = NoteAdapter(notes)
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
        binding.getNotes.setOnClickListener {
            noteViewModel.loadNotes()
        }
        binding.bindingTest = "hello!"
        return binding.root
    }
}
