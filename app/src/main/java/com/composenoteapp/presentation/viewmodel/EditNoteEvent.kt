package com.composenoteapp.presentation.viewmodel

import androidx.compose.ui.focus.FocusState

sealed class EditNoteEvent {
    data class EnteredTitle(val value: String) : EditNoteEvent()
    data class ChangeTitleFocus(val focusSate: FocusState) : EditNoteEvent()
    data class EnteredContent(val value: String) : EditNoteEvent()
    data class ChangeContentFocus(val focusSate: FocusState) : EditNoteEvent()
    object SaveNote : EditNoteEvent()
}
