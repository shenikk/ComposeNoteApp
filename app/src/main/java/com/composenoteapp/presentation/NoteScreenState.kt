package com.composenoteapp.presentation

import com.composenoteapp.models.NoteItemModel

data class NoteScreenState(
    val notes: List<NoteItemModel> = emptyList()
)