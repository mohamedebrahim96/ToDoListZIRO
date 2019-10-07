package com.example.todolistziro.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.example.todolistziro.R
import com.example.todolistziro.data.Note
import java.util.ArrayList

class NoteAdapter : RecyclerView.Adapter<NoteAdapter.NoteHolder>() {


    private var notes: List<Note> = ArrayList()



    @NonNull
    override fun onCreateViewHolder(@NonNull viewGroup: ViewGroup, position: Int): NoteHolder {
        val itemView = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.note_item, viewGroup, false)
        return NoteHolder(itemView)
    }

    override fun onBindViewHolder(@NonNull noteHolder: NoteHolder, position: Int) {
        val currentNote = notes[position]
        noteHolder.textViewTitle.setText(currentNote.title)
        noteHolder.textViewDescription.setText(currentNote.description)
        noteHolder.textViewPriority.setText(currentNote.priority.toString())
    }

    fun setNotes(notes: List<Note>) {
        this.notes = notes
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return notes.size
    }
    
    fun getNoteAt(position: Int): Note {
        return notes[position]
    }

    inner class NoteHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewTitle: TextView
        val textViewDescription: TextView
        val textViewPriority: TextView

        init {
            textViewTitle = itemView.findViewById(R.id.text_view_title)
            textViewDescription = itemView.findViewById(R.id.text_view_description)
            textViewPriority = itemView.findViewById(R.id.text_view_priority)
        }

    }
}
