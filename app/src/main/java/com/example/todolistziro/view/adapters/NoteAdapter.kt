package com.example.todolistziro.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.example.todolistziro.R
import com.example.todolistziro.data.Note
import com.example.todolistziro.viewmodel.NoteViewModel
import java.util.ArrayList

class NoteAdapter(private val context: Context, private var noteViewModel:NoteViewModel?)
    : RecyclerView.Adapter<NoteAdapter.NoteHolder>() {
    private var notes: List<Note> = ArrayList()

    @NonNull
    override fun onCreateViewHolder(@NonNull viewGroup: ViewGroup, position: Int): NoteHolder {
        val itemView = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.note_item, viewGroup, false)
        return NoteHolder(itemView)
    }

    override fun onBindViewHolder(@NonNull noteHolder: NoteHolder, position: Int) {
        val currentNote = notes[position]
        noteHolder.textViewTitle.text = currentNote.title
        noteHolder.textViewTitle.setTextColor(context.resources.getColor(getColor(currentNote.priority)))
        noteHolder.textViewDescription.text = currentNote.description
        noteHolder.type.setBackgroundResource(getColor(currentNote.priority))
        noteHolder.priority.text = getType(currentNote.priority)
        noteHolder.priority.setCompoundDrawablesWithIntrinsicBounds(getDrawable(currentNote.priority),0,0,0)

        noteHolder.close.setOnClickListener {
            noteViewModel!!.delete(getNoteAt(position))
        }
    }

    private fun getColor(priority: Int):Int {
        return when (priority) {
            0 -> R.color.orange
            1 -> R.color.purple
            2 -> R.color.blue
            else -> R.color.orange
        }
    }

    private fun getDrawable(priority: Int):Int {
        return when (priority) {
            0 -> R.drawable.ic_circle_orange
            1 -> R.drawable.ic_circle_purple
            2 -> R.drawable.ic_circle_blue
            else -> R.drawable.ic_circle_orange
        }
    }
    private fun getType(priority: Int):String {
        return when (priority) {
            0 -> "Work"
            1 -> "Personal"
            2 -> "Health"
            else -> "Work"
        }
    }

    fun setNotes(notes: List<Note>) {
        this.notes = notes
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return notes.size
    }
    
    fun getNoteAt(position: Int): Int {
        return notes[position].id
    }

    inner class NoteHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val textViewTitle: TextView = itemView.findViewById(R.id.text_view_title)
        val priority: TextView = itemView.findViewById(R.id.priority)
        val textViewDescription: TextView = itemView.findViewById(R.id.text_view_description)
        val type: View = itemView.findViewById(R.id.type)
        val close: ImageView = itemView.findViewById(R.id.close)
    }
}
