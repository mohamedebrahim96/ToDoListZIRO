package com.example.todolistziro.viewmodel

import android.app.Application
import androidx.annotation.NonNull
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.todolistziro.data.Note
import com.example.todolistziro.data.NoteRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class NoteViewModel(@NonNull application: Application) : AndroidViewModel(application) {

    private val repository: NoteRepository = NoteRepository(application)
    val allNotes: LiveData<List<Note>>

    init {
        allNotes = repository.allNotes
    }

    fun insert(note: Note) {
        viewModelScope.launch {
            repository.insert(note)
        }
    }

    fun update(note: Note) {
        viewModelScope.launch {
            repository.update(note)
        }
    }

    fun delete(noteId: Int) {
        viewModelScope.launch {
            repository.delete(noteId)
        }
    }

    fun deleteAllNotes() {
        viewModelScope.launch {
            repository.deleteAllNotes()
        }
    }
}