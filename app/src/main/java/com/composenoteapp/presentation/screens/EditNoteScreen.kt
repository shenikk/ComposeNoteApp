package com.composenoteapp.presentation.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun EditNoteScreen(navController: NavController) {
    Scaffold(floatingActionButton = { EditNoteFloatingActionButton(navController) }) {
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "There will be edit content")
    }
}

@Composable
fun EditNoteFloatingActionButton(navController: NavController) {
    val context = LocalContext.current
    FloatingActionButton(onClick = {
        navController.popBackStack()
        Toast.makeText(context, "Note saved", Toast.LENGTH_SHORT).show()
    }) {
        Icon(Icons.Filled.Create, "Add a note")
    }
}