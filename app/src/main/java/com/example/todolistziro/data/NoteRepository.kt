package com.example.todolistziro.data

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NoteRepository(application: Application, private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO) {
    private val noteDao: NoteDao
    val allNotes: LiveData<List<Note>>

    init {
        val noteDatabase = NoteDatabase.getInstance(application)
        noteDao = noteDatabase.noteDao()
        allNotes = noteDao.allNotes
    }

    suspend fun insert(note: Note) = withContext(ioDispatcher) {
        noteDao.insert(note)
    }

    suspend fun update(note: Note) = withContext<Unit>(ioDispatcher) {
        noteDao.update(note)
    }

    suspend fun delete(noteId: Int) = withContext<Unit>(ioDispatcher) {
        noteDao.delete(noteId)
    }

    suspend fun deleteAllNotes() = withContext(ioDispatcher) {
        noteDao.deleteAllNotes()
    }
}
