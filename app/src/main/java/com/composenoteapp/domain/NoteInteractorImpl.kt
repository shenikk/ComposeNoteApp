package com.composenoteapp.domain

import com.composenoteapp.models.NoteEntity
import kotlinx.coroutines.flow.Flow

class NoteInteractorImpl(
    private val repository: NoteRepository
) : NoteInteractor {

    override fun getNotes(): Flow<List<NoteEntity>> = repository.getNotes()

    override suspend fun getNoteById(id: Int): NoteEntity? = repository.getNoteById(id)

    override suspend fun addNote(note: NoteEntity) {
        repository.addNote(note)
    }

    override suspend fun deleteNote(note: NoteEntity) {
        repository.deleteNote(note)
    }
}
