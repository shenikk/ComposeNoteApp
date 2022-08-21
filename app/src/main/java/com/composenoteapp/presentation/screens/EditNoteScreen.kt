package com.composenoteapp.presentation.screens

import androidx.compose.animation.Animatable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.composenoteapp.R
import com.composenoteapp.presentation.components.TransparentHintTextField
import com.composenoteapp.presentation.viewmodel.EditNoteEvent
import com.composenoteapp.presentation.viewmodel.EditNoteScreenViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
fun EditNoteScreen(
    navController: NavController,
    noteColor: Int,
    viewModel: EditNoteScreenViewModel = hiltViewModel()
) {
    val noteTitleState = viewModel.noteTitle.value
    val noteContentState = viewModel.noteContent.value
    val scaffoldState = rememberScaffoldState()

    val noteBackgroundAnimatable = remember {
        Animatable(
            Color(if (noteColor != -1) noteColor else viewModel.noteColor.value)
        )
    }

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is EditNoteScreenViewModel.UiEvent.ShowSnackBar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }
                is EditNoteScreenViewModel.UiEvent.SaveNote -> {
                    navController.navigateUp()
                }
            }
        }
    }

    Scaffold(floatingActionButton = { EditNoteFloatingActionButton(viewModel) }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(noteBackgroundAnimatable.value)
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
fun EditNoteFloatingActionButton(viewModel: EditNoteScreenViewModel) {
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    FloatingActionButton(onClick = {
        scope.launch {
            viewModel.onEvent(EditNoteEvent.SaveNote)
        }
    }) {
        Icon(Icons.Default.Save, context.getString(R.string.add_note))
    }
}
