package com.example.roommvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

// Notes application
// local storage component of jetpack - Room Database

// Room Components
//  1. Database -> Abstract class that inherits room database annotated with Databse annotation
//  2. Entity -> Data Class -> @Entity -> Data class denoting the columns of the table
//  3. Dao -> Database access objects -> Queries and functions are written here @Dao
//

// Flow -> [ Entity -> Dao -> Database -> Repository -> ViewModel ]

// Notes application
class MainActivity : AppCompatActivity(), NoteRVAdapter {
    lateinit var noteText:EditText
    lateinit var viewModel: NoteViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        noteText = findViewById<EditText>(R.id.edtv)

        val recyclerView = findViewById<RecyclerView>(R.id.noteRv)

        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = NoteAdapter(this)

        recyclerView.adapter = adapter

        viewModel = ViewModelProvider(this,
        ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NoteViewModel::class.java)


        viewModel.allNotes.observe(
            this,
            Observer {
                adapter.updateList(it)
            }
        )


    }

    override fun onItemClicked(noteEntity: NoteEntity) {
        viewModel.deleteNode(note = noteEntity)
    }

    fun submitData(view: android.view.View) {
        if(noteText.text.isNotEmpty()){
            viewModel.insertNote(NoteEntity(noteText.text.toString()))
        }
    }
}