package com.example.roommvvm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application):AndroidViewModel(application) {

    lateinit var allNotes:LiveData<List<NoteEntity>>
    lateinit var repository: NoteRepository
    init {
        val dao = NoteDatabase.getDatabase(application).NoteDao()

        repository = NoteRepository(dao)

        allNotes = repository.allNotes
    }

    fun deleteNode(note: NoteEntity) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(note)
    }

    fun insertNote(note: NoteEntity) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(note)
    }

}