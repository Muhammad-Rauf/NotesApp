package com.example.notesapp.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.room.Database
import com.example.notesapp.Database.NotesDatabase
import com.example.notesapp.Model.Notes
import com.example.notesapp.Repository.NotesRepository

class NotesViewModel(application: Application):AndroidViewModel(application) {
    var repository:NotesRepository
    init {

        var dao=NotesDatabase.getDatabaseInstance(application).myNotesDao()
        repository= NotesRepository(dao)

    }

    fun getNotes():LiveData<List<Notes>> = repository.getAllNotes()

    fun addNotes(notes: Notes){
        repository.insertNotes(notes)
    }

    fun deleteNotes(id:Int){
        repository.deleteNotes(id)
    }

    fun updateNotes(notes: Notes){
        repository.updateNotes(notes)

    }
    //////filtering
    fun getLowNotes():LiveData<List<Notes>> = repository.getLowNotes()
    fun getMediumNotes():LiveData<List<Notes>> = repository.getMediumNotes()
    fun getHighNotes():LiveData<List<Notes>> = repository.getHighNotes()
    ////
    fun getFavouriteNotes():LiveData<List<Notes>> = repository.getFavNotes()

}