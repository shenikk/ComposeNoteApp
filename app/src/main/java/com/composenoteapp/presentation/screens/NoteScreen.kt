package com.composenoteapp.presentation.screens

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.composenoteapp.list
import com.composenoteapp.presentation.NoteItem

@Composable
fun NoteScreen(navController: NavController) {
    Scaffold(floatingActionButton = { NoteFloatingActionButton(navController) }) {
        LazyColumn {
            items(list) {
                Spacer(modifier = Modifier.height(16.dp))
                NoteItem(noteTitle = it.title, noteContent = it.content, noteColor = it.color)
            }
        }
    }
}

@Composable
fun NoteFloatingActionButton(navController: NavController) {
    FloatingActionButton(onClick = { navController.navigate(Screen.EditNoteScreen.route) }) {
        Icon(Icons.Filled.Add, "Add a note")
    }
}
