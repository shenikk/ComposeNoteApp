package com.composenoteapp.presentation.viewmodel

import com.composenoteapp.models.NoteEntity

sealed class NoteEvent {
    object GetNotesEvent : NoteEvent()
    data class DeleteEvent(val noteEntity: NoteEntity) : NoteEvent()
    object RestoreEvent : NoteEvent()
}
