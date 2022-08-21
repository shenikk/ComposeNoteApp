package com.composenoteapp.data

import com.composenoteapp.data.database.NoteDao
import com.composenoteapp.domain.NoteRepository
import com.composenoteapp.models.NoteEntity
import kotlinx.coroutines.flow.Flow

class NoteRepositoryImpl(
    private val dao: NoteDao
) : NoteRepository {

    override fun getNotes(): Flow<List<NoteEntity>> = dao.getNotes()

    override suspend fun getNoteById(id: Int): NoteEntity? = dao.getNoteById(id)

    override suspend fun addNote(note: NoteEntity) {
        dao.addNote(note)
    }

    override suspend fun deleteNote(note: NoteEntity) {
        dao.deleteNote(note)
    }
}
