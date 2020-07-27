package com.example.noteapplication.app.ui.features.notes

import android.util.Log
import android.view.View
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.noteapplication.R
import com.example.noteapplication.domain.models.NoteModel

class NoteClickListener: View.OnClickListener {

    override fun onClick(noteView: View?) {
        Log.d("ds", "s")
        noteView?.let {
            Navigation.findNavController(noteView).navigate(R.id.editNoteFragment)
        }
    }

    fun onClick(noteView: View?, model: NoteModel?) {
        noteView?.let {
            model?.let { model ->
                val action = EditNoteFragmentDirections.goToEditNote(model)
                Navigation.findNavController(noteView).navigate(action)
            }
        }
    }
}