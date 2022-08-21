package com.composenoteapp.domain

import com.composenoteapp.models.NoteEntity
import kotlinx.coroutines.flow.Flow

interface NoteInteractor {

    fun getNotes(): Flow<List<NoteEntity>>

    suspend fun getNoteById(id: Int): NoteEntity?

    suspend fun addNote(note: NoteEntity)

    suspend fun deleteNote(note: NoteEntity)
}
