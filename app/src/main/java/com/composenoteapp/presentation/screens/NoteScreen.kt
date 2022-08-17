package com.composenoteapp.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.composenoteapp.models.NoteItemModel
import com.composenoteapp.presentation.NoteItem
import com.composenoteapp.presentation.viewmodel.NoteScreenViewModel

@Composable
fun NoteScreen(navController: NavController) {
    val viewModel: NoteScreenViewModel = viewModel()
    val state = viewModel.noteState.value

    if (state.notes.isNotEmpty()) {
        ContentScreen(navController, state.notes)
    } else {
        EmptyScreen(navController)
    }
}

@Composable
private fun ContentScreen(navController: NavController, notes: List<NoteItemModel>) {
    Scaffold(floatingActionButton = { NoteFloatingActionButton(navController) }) {
        LazyColumn {
            items(notes) { notes ->
                Spacer(modifier = Modifier.height(16.dp))
                NoteItem(
                    noteTitle = notes.title,
                    noteContent = notes.content,
                    noteColor = notes.color
                )
            }
        }
    }
}

@Composable
private fun EmptyScreen(navController: NavController) {
    Scaffold(floatingActionButton = { NoteFloatingActionButton(navController) }) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxHeight().fillMaxWidth()
        ) {
            Text(
                "Add your first note",
                Modifier.padding(16.dp),
                textAlign = TextAlign.Center,
                style = typography.h5,
            )
        }
    }
}

@Composable
fun NoteFloatingActionButton(navController: NavController) {
    FloatingActionButton(onClick = { navController.navigate(Screen.EditNoteScreen.route) }) {
        Icon(Icons.Filled.Add, "Add a note")
    }
}
