package com.composenoteapp.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.composenoteapp.domain.NoteInteractor
import com.composenoteapp.models.NoteEntity
import com.composenoteapp.presentation.screens.NoteScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteScreenViewModel @Inject constructor(
    private val noteInteractor: NoteInteractor
) : ViewModel() {

    private val _noteState = mutableStateOf(NoteScreenState())
    val noteState: State<NoteScreenState> = _noteState

    private var getNotesJob: Job? = null

    private var recentlyDeletedNote: NoteEntity? = null

    init {
        getNotes()
    }

    fun onEvent(event: NoteEvent) {
        when (event) {
            is NoteEvent.GetNotesEvent -> {
                viewModelScope.launch {
                    noteInteractor.getNotes()
                }
            }
            is NoteEvent.DeleteEvent -> {
                viewModelScope.launch {
                    noteInteractor.deleteNote(event.noteEntity)
                    recentlyDeletedNote = event.noteEntity
                }
            }
            is NoteEvent.RestoreEvent -> {
                viewModelScope.launch {
                    noteInteractor.addNote(recentlyDeletedNote ?: return@launch)
                    recentlyDeletedNote = null
                }
            }
        }
    }

    private fun getNotes() {
        getNotesJob?.cancel()
        getNotesJob = noteInteractor.getNotes()
            .onEach { notes ->
                _noteState.value = noteState.value.copy(
                    notes = notes
                )
            }
            .launchIn(viewModelScope)
    }
}
