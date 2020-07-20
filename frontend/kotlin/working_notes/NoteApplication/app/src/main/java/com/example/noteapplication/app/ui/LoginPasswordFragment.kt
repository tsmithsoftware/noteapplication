package com.example.noteapplication.app.ui

import android.content.Context
import android.util.Log
import androidx.fragment.app.Fragment
import com.example.noteapplication.app.viewmodel.NoteViewModel
import javax.inject.Inject

class LoginPasswordFragment: Fragment() {

    // Fields that need to be injected by the login graph
    @Inject
    lateinit var loginViewModel: NoteViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)

        (activity as MainActivity).notesComponent.inject(this)

        Log.d("Accessing vm:", loginViewModel.stringValue)
    }
}
