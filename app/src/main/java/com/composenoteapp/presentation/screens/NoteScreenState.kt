package com.composenoteapp.presentation.screens

import com.composenoteapp.models.NoteItemModel

data class NoteScreenState(
    val notes: List<NoteItemModel> = emptyList()
)