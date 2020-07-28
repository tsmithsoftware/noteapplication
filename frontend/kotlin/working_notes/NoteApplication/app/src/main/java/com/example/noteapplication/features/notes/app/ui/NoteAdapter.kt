package com.example.noteapplication.features.notes.app.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapplication.R
import com.example.noteapplication.databinding.NoteLayoutBinding
import com.example.noteapplication.features.notes.domain.models.NoteModel

class NoteAdapter(
    private val dataset: List<NoteModel>,
    private val clickListener: NoteClickListener,
    private val deleteListener: NoteClickListener
) :
    RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    class NoteViewHolder(private val noteBinding: NoteLayoutBinding) : RecyclerView.ViewHolder(noteBinding.root) {

        fun bindItem(note: NoteModel, itemClickListener: NoteClickListener, deleteListener: NoteClickListener) {
            noteBinding.openClickListener = itemClickListener
            noteBinding.deleteClickListener = deleteListener
            noteBinding.model = note
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): NoteViewHolder {
        val noteLayoutBinding = DataBindingUtil.inflate<NoteLayoutBinding>(LayoutInflater.from(parent.context), R.layout.note_layout, parent, false)
        return NoteViewHolder(noteLayoutBinding)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bindItem(dataset[position], clickListener, deleteListener)
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataset.size
}
