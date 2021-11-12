package com.example.roommvvm

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(noteEntity: NoteEntity)

    @Delete
    suspend fun delete(noteEntity: NoteEntity)

    @Query("Select * From NotesTable order by id asc")
    fun getAllNotes() : LiveData<List<NoteEntity>>
}