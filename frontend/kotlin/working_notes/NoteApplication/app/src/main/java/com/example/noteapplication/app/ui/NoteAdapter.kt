package com.example.noteapplication.app.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapplication.R
import com.example.noteapplication.app.ui.features.notes.NoteClickListener
import com.example.noteapplication.data.models.NoteDataModel
import com.example.noteapplication.databinding.NoteLayoutBinding
import com.example.noteapplication.domain.models.NoteModel

class NoteAdapter(private val dataset: List<NoteModel>) :
    RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    private val itemClickListener = NoteClickListener()

    class NoteViewHolder(private val noteBinding: NoteLayoutBinding) : RecyclerView.ViewHolder(noteBinding.root) {

        fun bindItem(note: NoteModel, itemClickListener: NoteClickListener) {
            noteBinding.clickListener = itemClickListener
            noteBinding.model = note
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): NoteViewHolder {
        val noteLayoutBinding = DataBindingUtil.inflate<NoteLayoutBinding>(LayoutInflater.from(parent.context), R.layout.note_layout, parent, false)
        return NoteViewHolder(noteLayoutBinding)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bindItem(dataset[position], itemClickListener)
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataset.size
}
