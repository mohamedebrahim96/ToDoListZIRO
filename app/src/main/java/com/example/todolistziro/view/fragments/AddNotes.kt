package com.example.todolistziro.view.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.todolistziro.R
import com.example.todolistziro.data.Note
import com.example.todolistziro.utils.Constants
import com.example.todolistziro.viewmodel.NoteViewModel
import kotlinx.android.synthetic.main.fragment_add_notes.*

class AddNotes : BaseFragment() {

    var priority: Int = 0
    private var noteViewModel: NoteViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_notes, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel::class.java)

        work.setOnClickListener { priority = 0 }
        personal.setOnClickListener { priority = 1 }
        health.setOnClickListener { priority = 2 }
        addTask.setOnClickListener { saveNote() }
    }

    private fun saveNote() {
        val title = editTextTitle?.text.toString().trim { it <= ' ' }
        val description = editTextDescription?.text.toString().trim { it <= ' ' }

        if (title.isEmpty() || description.isEmpty()) {
            Toast.makeText(
                this.activity, "Please inssert a title and description",
                Toast.LENGTH_LONG
            ).show()
            return
        }

        val data = Intent()
        data.putExtra(Constants.EXTRA_TITLE, title)
        data.putExtra(Constants.EXTRA_DESCRIPTION, description)
        data.putExtra(Constants.EXTRA_PRIORITY, priority)

        /*val title = data!!.getStringExtra(EXTRA_TITLE)
        val description = data!!.getStringExtra(EXTRA_DESCRIPTION)
        val priority = data!!.getIntExtra(EXTRA_PRIORITY, 1)*/

        val note = Note(title, description, priority)
        noteViewModel!!.insert(note)

        findNavController().navigate(R.id.destination_home)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        /*if (requestCode == activity.ADD_NOTE_REQUEST && resultCode == RESULT_OK) {
            val title = data!!.getStringExtra(EXTRA_TITLE)
            val description = data!!.getStringExtra(EXTRA_DESCRIPTION)
            val priority = data!!.getIntExtra(EXTRA_PRIORITY, 1)

            val note = Note(title, description, priority)
            noteViewModel!!.insert(note)

            Toast.makeText(this.activity, "Note saved", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this.activity, "Note not saved", Toast.LENGTH_SHORT).show()
        }*/
    }

}