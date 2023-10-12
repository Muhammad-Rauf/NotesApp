package com.example.notesapp.DAO

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.notesapp.Model.Notes
import java.net.IDN

@Dao
interface NotesDao {
    @Query("SELECT * FROM Notes ORDER BY notes_id DESC")
    fun getNotes():LiveData<List<Notes>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNotes(notes: Notes)

    @Query("DELETE FROM Notes WHERE notes_id=:id")
    fun deleteNotes(id:Int)


    @Update
    fun updateNotes(notes: Notes)

    /////////filtering
    @Query("SELECT * FROM Notes WHERE priority=3")
    fun getHighNotes():LiveData<List<Notes>>

    @Query("SELECT * FROM Notes WHERE priority=2")
    fun getMediumNotes():LiveData<List<Notes>>

    @Query("SELECT * FROM Notes WHERE priority=1")
    fun getLowNotes():LiveData<List<Notes>>
    ////Get all favourite

    @Query("SELECT * FROM Notes WHERE isFavourite=1")
    fun getFavNotes():LiveData<List<Notes>>


    @Query("UPDATE  Notes SET isFavourite = :isFavorite WHERE notes_id = :id")
    fun favourite_Notes(isFavorite: Boolean, id: Int)

}
val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(
            "ALTER TABLE Notes ADD COLUMN isFavourite INTEGER default 0 NOT NULL"
        )

    }

}