package com.composenoteapp.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.ViewModel
import com.composenoteapp.models.NoteItemModel
import com.composenoteapp.presentation.screens.NoteScreenState

class NoteScreenViewModel : ViewModel() {

    private val _noteState = mutableStateOf(NoteScreenState())
    val noteState: State<NoteScreenState> = _noteState

    init {
        _noteState.value = NoteScreenState(getNotes())
    }

    fun onEvent(event: NoteEvent) {
        when (event) {
            is NoteEvent.AddEvent -> {
                //TODO
            }
            is NoteEvent.DeleteEvent -> {
                //TODO
            }
            is NoteEvent.RestoreEvent -> {
                //TODO
            }
        }
    }

    private fun getNotes(): List<NoteItemModel> {
        // TODO it's a mock. Delete it later
        return listOf(
            NoteItemModel(
                title = "First Title", content = "bla bla bla bla bla bla bla bla bla bla bla bla",
                NoteItemModel.noteColor.random().toArgb()
            ),
            NoteItemModel(
                title = "Second Title",
                content = "bla bla bla",
                NoteItemModel.noteColor.random().toArgb()
            ),
            NoteItemModel(
                title = "Third Title",
                content = "bla bla bla",
                NoteItemModel.noteColor.random().toArgb()
            )
        )
//        return emptyList()
    }
}