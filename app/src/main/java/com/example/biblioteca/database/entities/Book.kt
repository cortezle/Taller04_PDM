package com.example.biblioteca.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "book_table")
data class Book(
    var title : String,
    var authors : ArrayList<Author>,
    var cover : Byte,
    var edition : Int,
    var editorial : ArrayList<Editorial>,
    var synopsis : String,
    var tags : ArrayList<Tag>,
    var isbn : String,
    var favourite : Int
){@PrimaryKey(autoGenerate = true) var id : Long = 0}