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

    // Fields that need to be injected by the login graph
    @Inject
    lateinit var loginViewModel: NoteViewModel

    lateinit var binding: LoginUsernameFragmentBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)

        // Obtaining the login graph from LoginActivity and instantiate
        // the @Inject fields with objects from the graph
        (activity as MainActivity).notesComponent.inject(this)

        // Now you can access loginViewModel here and onCreateView too
        // (shared instance with the Activity and the other Fragment)
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
        return binding.root
    }
}
