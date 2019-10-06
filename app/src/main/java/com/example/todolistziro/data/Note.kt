package com.example.todolistziro.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note_table")
class Note(val title: String, val description: String, val priority: Int) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
