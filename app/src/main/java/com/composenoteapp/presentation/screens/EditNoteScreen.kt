package com.composenoteapp.presentation.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.composenoteapp.presentation.components.TransparentHintTextField
import com.composenoteapp.presentation.viewmodel.EditNoteEvent
import com.composenoteapp.presentation.viewmodel.EditNoteScreenViewModel

@Composable
fun EditNoteScreen(navController: NavController) {
    val viewModel: EditNoteScreenViewModel = viewModel()
    val noteTitleState = viewModel.noteTitle.value
    val noteContentState = viewModel.noteContent.value

    Scaffold(floatingActionButton = { EditNoteFloatingActionButton(navController) }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            TransparentHintTextField(
                text = noteTitleState.text,
                hint = noteTitleState.hint,
                onValueChange = {
                    viewModel.onEvent(EditNoteEvent.EnteredTitle(it))
                },
                onFocusChange = {
                    viewModel.onEvent(EditNoteEvent.ChangeTitleFocus(it))
                },
                isHintVisible = noteTitleState.isHintVisible,
                singleLine = true,
                textStyle = MaterialTheme.typography.h5
            )

            Spacer(modifier = Modifier.height(16.dp))

            TransparentHintTextField(
                text = noteContentState.text,
                hint = noteContentState.hint,
                onValueChange = {
                    viewModel.onEvent(EditNoteEvent.EnteredContent(it))
                },
                onFocusChange = {
                    viewModel.onEvent(EditNoteEvent.ChangeContentFocus(it))
                },
                isHintVisible = noteContentState.isHintVisible,
                singleLine = false,
                textStyle = MaterialTheme.typography.body1,
                modifier = Modifier.fillMaxHeight()
            )
        }
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