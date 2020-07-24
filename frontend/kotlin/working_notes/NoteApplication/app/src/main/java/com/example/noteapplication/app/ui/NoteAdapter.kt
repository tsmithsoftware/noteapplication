package com.example.noteapplication.app.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapplication.R
import com.example.noteapplication.data.models.NoteDataModel
import com.example.noteapplication.domain.models.NoteModel

class NoteAdapter(private val dataset: List<NoteModel>) :
    RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItem(note: NoteModel) {
            val noteTitle = itemView.findViewById(R.id.noteTitle) as TextView
            val noteDetails  = itemView.findViewById(R.id.noteDetails) as TextView
            noteTitle.text = note.noteTitle
            noteDetails.text = note.noteDetails
        }
    }


    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): NoteAdapter.NoteViewHolder {
        // create a new view
        val viewHolder = LayoutInflater.from(parent.context)
            .inflate(R.layout.note_layout, parent, false)
        return NoteViewHolder(viewHolder)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bindItem(dataset[position])
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataset.size
}
