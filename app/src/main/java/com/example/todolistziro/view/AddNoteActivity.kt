package com.example.todolistziro.view

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.todolistziro.R
import kotlinx.android.synthetic.main.activity_add_note.*

class AddNoteActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)

        getSupportActionBar()!!.setHomeAsUpIndicator(R.drawable.ic_close)
        setTitle("Add Task")
    }

    private fun saveNote() {
        val title = editTextTitle?.text.toString().trim { it <= ' ' }
        val description = editTextDescription?.text.toString().trim { it <= ' ' }
        //val priority = numberPickerPriority?.value

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
        data.putExtra(EXTRA_PRIORITY, 5)

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

    companion object {
        val EXTRA_TITLE = "EXTRA_TITLE"
        val EXTRA_DESCRIPTION = "EXTRA_DESCRIPTION"
        val EXTRA_PRIORITY = "EXTRA_PRIORITY"
    }
}
