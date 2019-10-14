package com.example.todolistziro.view.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todolistziro.data.Note
import com.example.todolistziro.view.adapters.NoteAdapter
import com.example.todolistziro.viewmodel.NoteViewModel
import kotlinx.android.synthetic.main.fragment_all_notes.*
import androidx.appcompat.app.AppCompatActivity
import com.example.todolistziro.R
import kotlinx.android.synthetic.main.activity_main.*


class AllNotes : BaseFragment() {

    private var noteViewModel: NoteViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_all_notes, container, false)

        return mView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        recyclerView.setLayoutManager(LinearLayoutManager(activity))
        recyclerView.setHasFixedSize(true)
        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel::class.java)

        val adapter = NoteAdapter(this.activity!!, noteViewModel)
        recyclerView.setAdapter(adapter)


        noteViewModel!!.allNotes.observe(this, object : Observer<List<Note>> {
            override fun onChanged(@Nullable notes: List<Note>) {
                // Update recycler view
                if(notes.size == 0){
                    recyclerView.visibility = View.GONE
                    empty_layout.visibility = View.VISIBLE
                }else{
                    recyclerView.visibility = View.VISIBLE
                    empty_layout.visibility = View.GONE
                    adapter.setNotes(notes)
                }
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
                Toast.makeText(activity, "Note deleted", Toast.LENGTH_SHORT).show()
            }
        }).attachToRecyclerView(recyclerView)

    }

    /*override fun onCreateOptionsMenu(menu: Menu): Boolean {
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
    }*/

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