package com.example.todolistziro.view

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todolistziro.R
import com.example.todolistziro.data.Note
import com.example.todolistziro.viewmodel.NoteViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val ADD_NOTE_REQUEST = 1

    private var noteViewModel: NoteViewModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button_add_note.setOnClickListener{
            val intent = Intent(this@MainActivity, AddNoteActivity::class.java)
            startActivityForResult(intent, ADD_NOTE_REQUEST)
        }

        recyclerView.setLayoutManager(LinearLayoutManager(this))
        recyclerView.setHasFixedSize(true)

        val adapter = NoteAdapter(this)
        recyclerView.setAdapter(adapter)


        noteViewModel = ViewModelProviders.of(this@MainActivity).get(NoteViewModel::class.java)
        noteViewModel!!.allNotes.observe(this, object : Observer<List<Note>>{
            override fun onChanged(@Nullable notes: List<Note>) {
                // Update recycler view
                adapter.setNotes(notes)
            }
        })

        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            // For drag and drop
            override fun onMove(
                @NonNull recyclerView: RecyclerView,
                @NonNull viewHolder: RecyclerView.ViewHolder,
                @NonNull viewHolder1: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(@NonNull viewHolder: RecyclerView.ViewHolder, direction: Int) {
                noteViewModel!!.delete(adapter.getNoteAt(viewHolder.getAdapterPosition()))
                Toast.makeText(this@MainActivity, "Note deleted", Toast.LENGTH_SHORT).show()
            }
        }).attachToRecyclerView(recyclerView)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.todolist_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.delete_all_notes -> {
                noteViewModel!!.deleteAllNotes()
                Toast.makeText(this, "All notes deleted", Toast.LENGTH_SHORT).show()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int,data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == ADD_NOTE_REQUEST && resultCode == RESULT_OK) {
            val title = data!!.getStringExtra(AddNoteActivity.EXTRA_TITLE)
            val description = data!!.getStringExtra(AddNoteActivity.EXTRA_DESCRIPTION)
            val priority = data!!.getIntExtra(AddNoteActivity.EXTRA_PRIORITY, 1)

            val note = Note(title, description, priority)
            noteViewModel!!.insert(note)

            Toast.makeText(this, "Note saved", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Note not saved", Toast.LENGTH_SHORT).show()
        }
    }
}
