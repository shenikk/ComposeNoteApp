package com.composenoteapp.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.composenoteapp.presentation.NoteTextFieldState

class EditNoteScreenViewModel : ViewModel() {

    private val _noteTitle = mutableStateOf(
        NoteTextFieldState(hint = "Enter title...")
    )
    val noteTitle: State<NoteTextFieldState> = _noteTitle

    private val _noteContent = mutableStateOf(
        NoteTextFieldState(hint = "Enter content...")
    )
    val noteContent: State<NoteTextFieldState> = _noteContent

    fun onEvent(event: EditNoteEvent) {
        when (event) {
            is EditNoteEvent.EnteredTitle -> {
                _noteTitle.value = noteTitle.value.copy(
                    text = event.value
                )
            }
            is EditNoteEvent.EnteredContent -> {
                _noteContent.value = noteContent.value.copy(
                    text = event.value
                )
            }
            is EditNoteEvent.ChangeTitleFocus -> {
                _noteTitle.value = noteTitle.value.copy(
                    isHintVisible = !event.focusSate.isFocused && _noteTitle.value.text.isBlank()
                )
            }
            is EditNoteEvent.ChangeContentFocus -> {
                _noteContent.value = noteContent.value.copy(
                    isHintVisible = !event.focusSate.isFocused && _noteContent.value.text.isBlank()
                )
            }
            is EditNoteEvent.SaveNote -> {
                TODO()
            }
        }
    }
}