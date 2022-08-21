package com.composenoteapp.presentation.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun Navigation() {
    val nav = rememberNavController()

    NavHost(navController = nav, startDestination = Screen.NoteScreen.route) {
        composable(route = Screen.NoteScreen.route) {
            NoteScreen(nav)
        }
        composable(route = Screen.EditNoteScreen.route + "?noteId={noteId}&noteColor={noteColor}",
            arguments = listOf(
                navArgument(
                    name = "noteId"
                ) {
                    type = NavType.IntType
                    defaultValue = -1
                },
                navArgument(
                    name = "noteColor"
                ) {
                    type = NavType.IntType
                    defaultValue = -1
                }
            )) {
            val color = it.arguments?.getInt("noteColor") ?: -1
            EditNoteScreen(nav, color)
        }
    }
}
