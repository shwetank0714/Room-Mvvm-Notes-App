package com.example.roommvvm

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "NotesTable")
class NoteEntity(
    @ColumnInfo(name = "NoteText") val text: String) {

    @PrimaryKey(autoGenerate = true)
    var id = 0
}