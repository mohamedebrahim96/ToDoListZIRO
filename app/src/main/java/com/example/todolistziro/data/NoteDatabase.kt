package com.example.todolistziro.data

import android.content.Context
import androidx.annotation.NonNull
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.*

@Database(entities = [Note::class], version = 1)
abstract class NoteDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao

    companion object {
        var instance: NoteDatabase? = null
        @Synchronized
        fun getInstance(context: Context): NoteDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    NoteDatabase::class.java, "note_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build()
            }
            return instance as NoteDatabase
        }

        private val roomCallback = object : RoomDatabase.Callback() {
            override fun onCreate(@NonNull db: SupportSQLiteDatabase) {
                super.onCreate(db)
                GlobalScope.launch {
                    loadTask(instance!!)
                }
            }
        }

        suspend fun loadTask(instance: NoteDatabase) = withContext(Dispatchers.IO) {
            instance.noteDao().apply {
                insert(Note("Title 1", "Description 1", 1))
                insert(Note("Title 2", "Description 2", 2))
                insert(Note("Title 3", "Description 3", 3))
            }
        }
    }
}
