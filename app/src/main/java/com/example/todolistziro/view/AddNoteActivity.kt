package com.example.todolistziro.view

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.todolistziro.R
import com.example.todolistziro.utils.Constants.Companion.EXTRA_DESCRIPTION
import com.example.todolistziro.utils.Constants.Companion.EXTRA_PRIORITY
import com.example.todolistziro.utils.Constants.Companion.EXTRA_TITLE
import kotlinx.android.synthetic.main.fragment_add_notes.*

class AddNoteActivity : AppCompatActivity() {

    var priority:Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_add_notes)

        getSupportActionBar()!!.setHomeAsUpIndicator(R.drawable.ic_close)
        setTitle("Add Task")
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
                this, "Please inssert a title and description",
                Toast.LENGTH_LONG
            ).show()
            return
        }

        val data = Intent()
        data.putExtra(EXTRA_TITLE, title)
        data.putExtra(EXTRA_DESCRIPTION, description)
        data.putExtra(EXTRA_PRIORITY, priority)

        setResult(RESULT_OK, data)
        finish()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val menuInflater = getMenuInflater()
        menuInflater.inflate(R.menu.add_note_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.save_note -> {
                saveNote()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}
