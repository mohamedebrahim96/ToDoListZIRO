package com.example.todolistziro.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
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
        noteHolder.type.setBackgroundResource(getColor(currentNote.priority))
    }

    fun getColor(priority: Int):Int {
        if(priority == 0)
            return R.color.orange
        else if(priority == 1)
            return R.color.purple
        else if(priority == 2)
            return R.color.blue
        else
            return R.color.orange
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
        val type: View

        init {
            textViewTitle = itemView.findViewById(R.id.text_view_title)
            textViewDescription = itemView.findViewById(R.id.text_view_description)
            type = itemView.findViewById(R.id.type)
        }

    }
}
