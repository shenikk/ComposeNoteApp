package com.composenoteapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import com.composenoteapp.models.NoteItemModel
import com.composenoteapp.presentation.NoteItem
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
                    NoteScreen()
                }
            }
        }
    }
}

@Composable
fun NoteScreen() {
    LazyColumn {
        items(list) {
            Spacer(modifier = Modifier.height(16.dp))
            NoteItem(noteTitle = it.title, noteContent = it.content, noteColor = it.color)
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