package com.composenoteapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import com.composenoteapp.models.NoteItemModel
import com.composenoteapp.presentation.screens.Navigation
import com.composenoteapp.ui.theme.ComposeNoteAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeNoteAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Navigation()
                }
            }
        }
    }
}

// TODO it's a mock. Delete it later
val list = listOf(
    NoteItemModel(title = "First Title", content = "bla bla bla bla bla bla bla bla bla bla bla bla",
    NoteItemModel.noteColor.random().toArgb()),
    NoteItemModel(title = "Second Title", content = "bla bla bla", NoteItemModel.noteColor.random().toArgb()),
    NoteItemModel(title = "Third Title", content = "bla bla bla", NoteItemModel.noteColor.random().toArgb())
)
