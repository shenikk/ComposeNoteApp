package com.composenoteapp.presentation.screens

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.composenoteapp.R
import com.composenoteapp.models.NoteEntity
import com.composenoteapp.presentation.components.NoteItem
import com.composenoteapp.presentation.viewmodel.NoteEvent
import com.composenoteapp.presentation.viewmodel.NoteScreenViewModel
import kotlinx.coroutines.launch

@Composable
fun NoteScreen(
    navController: NavController,
    viewModel: NoteScreenViewModel = hiltViewModel()
) {
    val state = viewModel.noteState.value
    val context = LocalContext.current

    if (state.notes.isNotEmpty()) {
        ContentScreen(navController, state.notes, viewModel, context)
    } else {
        EmptyScreen(navController, context)
    }
}

@Composable
private fun ContentScreen(
    navController: NavController,
    notes: List<NoteEntity>,
    viewModel: NoteScreenViewModel,
    context: Context
) {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    Scaffold(floatingActionButton = { NoteFloatingActionButton(navController, context) }) {
        LazyColumn {
            items(notes) { note ->
                Spacer(modifier = Modifier.height(16.dp))
                NoteItem(
                    noteTitle = note.title,
                    noteContent = note.content,
                    noteColor = note.color,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            navController.navigate(Screen.EditNoteScreen.route + "?noteId=${note.id}&noteColor=${note.color}")
                        },
                    onDeleteClick = {
                        viewModel.onEvent(NoteEvent.DeleteEvent(note))
                        scope.launch {
                            val result = scaffoldState.snackbarHostState.showSnackbar(
                                message = context.getString(R.string.delete_note),
                                actionLabel = context.getString(R.string.undo)
                            )
                            if (result == SnackbarResult.ActionPerformed) {
                                viewModel.onEvent(NoteEvent.RestoreEvent)
                            }
                        }
                    }
                )
            }
        }
    }
}

@Composable
private fun EmptyScreen(navController: NavController, context: Context) {
    Scaffold(floatingActionButton = { NoteFloatingActionButton(navController, context) }) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
        ) {
            Text(
                context.getString(R.string.add_first_note),
                Modifier.padding(16.dp),
                textAlign = TextAlign.Center,
                style = typography.h5,
            )
        }
    }
}

@Composable
fun NoteFloatingActionButton(navController: NavController, context: Context) {
    FloatingActionButton(onClick = { navController.navigate(Screen.EditNoteScreen.route) }) {
        Icon(Icons.Filled.Add, context.getString(R.string.add_note))
    }
}
