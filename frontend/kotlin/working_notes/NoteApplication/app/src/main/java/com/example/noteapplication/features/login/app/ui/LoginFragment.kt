package com.example.noteapplication.features.login.app.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.example.noteapplication.R
import com.example.noteapplication.databinding.LoginFragmentBinding
import com.example.noteapplication.features.login.app.viewmodel.LoginViewModel
import com.example.noteapplication.shared.app.activities.MainActivity
import javax.inject.Inject

class LoginFragment: DialogFragment() {

    @Inject
    lateinit var loginViewModel: LoginViewModel

    lateinit var binding: LoginFragmentBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as MainActivity).notesComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding = DataBindingUtil.inflate(inflater,R.layout.login_fragment, container, false)
        val grantType = context?.getString(R.string.grant_type)
        val clientId = context?.getString(R.string.client_id)
        val clientSecret = context?.getString(R.string.client_secret)
        if (grantType != null && clientId != null && clientSecret != null) {
            binding.loginButton.setOnClickListener {
                loginViewModel.login(
                    grantType = grantType,
                    clientId = clientId,
                    clientSecret = clientSecret
                )
            }
        }
        return binding.root
    }

}