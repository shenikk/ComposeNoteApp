package com.composenoteapp.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.composenoteapp.ui.theme.LightRed
import com.composenoteapp.ui.theme.Pink
import com.composenoteapp.ui.theme.Purple200

@Entity
data class NoteEntity(
    val title: String,
    val content: String,
    val timeStamp: Long,
    val color: Int,
    @PrimaryKey val id: Int? = null
) {
    companion object {
        val noteColor = listOf(Pink, Purple200, LightRed)
    }
}
