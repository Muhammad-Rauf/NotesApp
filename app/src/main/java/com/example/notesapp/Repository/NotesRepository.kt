package com.example.notesapp.Repository

import androidx.lifecycle.LiveData
import com.example.notesapp.DAO.NotesDao
import com.example.notesapp.Model.Notes

class NotesRepository(var dao: NotesDao) {
    fun getAllNotes():LiveData<List<Notes>>{
        return dao.getNotes()
    }
    fun insertNotes(notes: Notes){
        dao.insertNotes(notes)
    }
    fun deleteNotes(id:Int){
        dao.deleteNotes(id)
    }
    fun updateNotes(notes: Notes){
        dao.updateNotes(notes)

    }
    ///////////filtering
   fun getLowNotes():LiveData<List<Notes>> = dao.getLowNotes()
   fun getMediumNotes():LiveData<List<Notes>> = dao.getMediumNotes()
   fun getHighNotes():LiveData<List<Notes>> = dao.getHighNotes()
////Get Fav

    fun getFavNotes():LiveData<List<Notes>> = dao.getFavNotes()

    fun favouriteNotes(isFav:Boolean,id:Int){
        dao.favourite_Notes(isFav,id)
    }
}