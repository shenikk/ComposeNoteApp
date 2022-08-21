package com.composenoteapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.composenoteapp.models.NoteEntity

@Database(
    entities = [NoteEntity::class],
    version = 1,
    exportSchema = false
)
abstract class NoteDatabase : RoomDatabase() {
    abstract val noteDao: NoteDao
}
