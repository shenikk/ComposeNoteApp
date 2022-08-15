package com.composenoteapp.presentation.screens

sealed class Screen(val route: String) {
    object NoteScreen : Screen("note_screen")
    object EditNoteScreen : Screen("edit_note_screen")
}
