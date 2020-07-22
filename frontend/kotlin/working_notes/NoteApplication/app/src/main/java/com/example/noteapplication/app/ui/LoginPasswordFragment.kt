package com.example.noteapplication.app.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.noteapplication.R
import com.example.noteapplication.app.viewmodel.NoteViewModel
import com.example.noteapplication.databinding.LoginPasswordFragmentBinding
import com.example.noteapplication.databinding.LoginUsernameFragmentBinding
import javax.inject.Inject

class LoginPasswordFragment: Fragment() {

    // Fields that need to be injected by the login graph
    @Inject
    lateinit var loginViewModel: NoteViewModel

    lateinit var binding: LoginPasswordFragmentBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)

        (activity as MainActivity).notesComponent.inject(this)

        Log.d("Accessing vm:", loginViewModel.stringValue)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.login_password_fragment, container, false )
        return binding.root
    }
}
