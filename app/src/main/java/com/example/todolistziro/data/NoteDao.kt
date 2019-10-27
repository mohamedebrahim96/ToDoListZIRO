package com.example.todolistziro.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NoteDao {

    @get:Query("SELECT * FROM note_table ORDER BY priority DESC")
    val allNotes: LiveData<List<Note>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(note: Note)

    @Update
    fun update(note: Note): Int

    @Query("DELETE FROM note_table WHERE id =:noteId")
    fun delete(noteId: Int): Int

    @Query("DELETE FROM note_table")
    fun deleteAllNotes()
}
