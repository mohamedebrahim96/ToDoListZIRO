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

class NoteAdapter(val context: Context,var noteViewModel:NoteViewModel?) : RecyclerView.Adapter<NoteAdapter.NoteHolder>() {
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
        noteHolder.textViewTitle.setTextColor(context.resources.getColor(getColor(currentNote.priority)))
        noteHolder.textViewDescription.setText(currentNote.description)
        noteHolder.type.setBackgroundResource(getColor(currentNote.priority))
        noteHolder.priority.setText(getType(currentNote.priority))
        noteHolder.priority.setCompoundDrawablesWithIntrinsicBounds(getDrawable(currentNote.priority),0,0,0)

        noteHolder.close.setOnClickListener {
            noteViewModel!!.delete(getNoteAt(position))
        }
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

    fun getDrawable(priority: Int):Int {
        if(priority == 0)
            return R.drawable.ic_circle_orange
        else if(priority == 1)
            return R.drawable.ic_circle_purple
        else if(priority == 2)
            return R.drawable.ic_circle_blue
        else
            return R.drawable.ic_circle_orange
    }
    fun getType(priority: Int):String {
        if(priority == 0)
            return "Work"
        else if(priority == 1)
            return "Personal"
        else if(priority == 2)
            return "Health"
        else
            return "Work"
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
        val priority: TextView
        val textViewDescription: TextView
        val type: View
        val close: ImageView

        init {
            textViewTitle = itemView.findViewById(R.id.text_view_title)
            textViewDescription = itemView.findViewById(R.id.text_view_description)
            type = itemView.findViewById(R.id.type)
            priority = itemView.findViewById(R.id.priority)
            close = itemView.findViewById(R.id.close)
        }


    }
}
