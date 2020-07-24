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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager


    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as MainActivity).notesComponent.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        noteViewModel.notes
            .subscribeOn(Schedulers.io())
            .subscribeWith(object: DisposableSingleObserver<List<NoteModel>>(),
                Observer<NoteModel> {
                override fun onSuccess(value: List<NoteModel>?) {
                    value?.let { notes ->
                        activity?.runOnUiThread { binding.recyclerView.adapter = NoteAdapter(notes) }
                    }
                    Log.d("Observer result:", "onSuccess: ${value?.size}")
                }

                override fun onError(e: Throwable?) {}
                override fun onComplete() {}
                override fun onNext(value: NoteModel?) {}
            })

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
        binding.bindingTest = "hello!"
        val adapter = NoteAdapter(listOf())
        binding.recyclerView.adapter = adapter
        return binding.root
    }
}
