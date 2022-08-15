package com.composenoteapp.presentation.screens

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun Navigation() {
    val nav = rememberNavController()

    NavHost(navController = nav, startDestination = Screen.NoteScreen.route) {
        composable(route = Screen.NoteScreen.route) {
            NoteScreen(nav)
        }
        composable(route = Screen.EditNoteScreen.route) {
            EditNoteScreen(nav)
        }
    }
}