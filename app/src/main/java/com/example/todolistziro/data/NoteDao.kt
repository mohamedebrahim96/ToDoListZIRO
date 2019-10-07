package com.example.todolistziro.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NoteDao {

    @get:Query("Select * from note_table order by priority desc")
    val allNotes: LiveData<List<Note>>

    @Insert
    fun insert(note: Note)

    @Update
    fun update(note: Note)

    @Delete
    fun delete(note: Note)

    @Query("Delete from note_table")
    fun deleteAllNotes()
}
