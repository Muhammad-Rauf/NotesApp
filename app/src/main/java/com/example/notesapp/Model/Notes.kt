package com.example.notesapp.Model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "Notes")

class Notes(
    @PrimaryKey(autoGenerate = true)
   var notes_id:Int?=null,
    var title:String,
    var sub_title:String,
    var notes:String,
    var date:String,
    var priority:String,
    var isFavourite:Boolean = false
):Parcelable
