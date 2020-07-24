package com.example.noteapplication.app.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.noteapplication.R
import com.example.noteapplication.app.viewmodel.NoteViewModel
import com.example.noteapplication.databinding.LoginUsernameFragmentBinding
import com.example.noteapplication.domain.models.NoteModel
import io.reactivex.Observer
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class LoginUsernameFragment: Fragment() {

    // Fields that need to be injected by the login graph
    @Inject
    lateinit var noteViewModel: NoteViewModel

    lateinit var binding: LoginUsernameFragmentBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)

        // Obtaining the login graph from LoginActivity and instantiate
        // the @Inject fields with objects from the graph
        (activity as MainActivity).notesComponent.inject(this)

        // Now you can access loginViewModel here and onCreateView too
        // (shared instance with the Activity and the other Fragment)

        // Now loginViewModel is available
        noteViewModel.notes
            .subscribeOn(Schedulers.io())
            .subscribeWith(object: DisposableSingleObserver<List<NoteModel>>(),
                Observer<NoteModel> {
                override fun onSuccess(value: List<NoteModel>?) {
                    Log.d("Observer result:", "onSuccess: ${value?.size}")
                }

                override fun onError(e: Throwable?) {
                    Log.d("Observer result:", "onError: ${e?.message}")
                }

                override fun onComplete() {
                    Log.d("Observer result:", "onComplete")
                }

                override fun onNext(value: NoteModel?) {
                    Log.d("Observer result:", "onNext note details: ${value?.noteDetails}")
                }
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
        return binding.root
    }
}
