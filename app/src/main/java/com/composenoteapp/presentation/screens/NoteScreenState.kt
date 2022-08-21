package com.composenoteapp.presentation.screens

import com.composenoteapp.models.NoteEntity

data class NoteScreenState(
    val notes: List<NoteEntity> = emptyList()
)