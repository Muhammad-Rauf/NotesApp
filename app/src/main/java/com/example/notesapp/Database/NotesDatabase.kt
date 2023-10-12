package com.example.notesapp.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.notesapp.DAO.MIGRATION_1_2
import com.example.notesapp.DAO.NotesDao
import com.example.notesapp.Model.Notes

@Database(entities = [Notes::class], version = 2, exportSchema = false)
abstract class NotesDatabase:RoomDatabase() {
    abstract fun myNotesDao():NotesDao

    companion object{
        @Volatile
        var INSTANCE:NotesDatabase?=null
        fun getDatabaseInstance(context: Context):NotesDatabase{
            val tempInstance= INSTANCE
            if (tempInstance !=null){
                return tempInstance
            }
            synchronized(this){
                var roomDatbaseInstance=Room.databaseBuilder(context,NotesDatabase::class.java,"Notes").addMigrations(
                    MIGRATION_1_2).allowMainThreadQueries().build()
                INSTANCE=roomDatbaseInstance
                return roomDatbaseInstance

            }
        }

    }
}
