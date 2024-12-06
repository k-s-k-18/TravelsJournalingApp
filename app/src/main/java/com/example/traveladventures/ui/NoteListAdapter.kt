package com.example.traveladventures.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.traveladventures.databinding.ListItemPlanBinding
import com.example.traveladventures.model.Note
import com.example.traveladventures.ui.activity.MainActivity
import com.example.traveladventures.ui.fragment.IndividualTripFragment

class NoteHolder(val binding: ListItemPlanBinding, private val context: IndividualTripFragment) : RecyclerView.ViewHolder(binding.root) {
    fun bind(note: Note) {
        binding.listTitle.text = note.noteTitle

        binding.root.setOnClickListener {
            val activity = context.activity as? MainActivity
            activity?.loadAddNote(note.noteId, note.tripId, note.userId)
        }
    }
}

class NoteListAdapter(private val notes: List<Note>, val context: IndividualTripFragment) : RecyclerView.Adapter<NoteHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemPlanBinding.inflate(inflater, parent, false)
        return NoteHolder(binding, context)
    }

    override fun getItemCount() = notes.size

    override fun onBindViewHolder(holder: NoteHolder, position: Int) {
        val note = notes[position]
        holder.bind(note)
    }
}