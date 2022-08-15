package com.composenoteapp.models

import com.composenoteapp.ui.theme.LightRed
import com.composenoteapp.ui.theme.Pink
import com.composenoteapp.ui.theme.Purple200

data class NoteItemModel(
    val title: String,
    val content: String,
    val color: Int
) {
    companion object {
        val noteColor = listOf(Pink, Purple200, LightRed)
    }
}
