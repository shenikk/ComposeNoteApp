package com.composenoteapp.presentation.viewmodel

sealed class NoteEvent {
    object AddEvent : NoteEvent()
    object DeleteEvent : NoteEvent()
    object RestoreEvent : NoteEvent()
}