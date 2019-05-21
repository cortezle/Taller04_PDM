package com.example.biblioteca.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "book_table")
data class Book(
    var title : String,
    var authors : String,
    var cover : Byte,
    var edition : Int,
    var editorial : String,
    var synopsis : String,
    var tags : String,
    var isbn : String,
    var favourite : Int
){@PrimaryKey(autoGenerate = true) var id : Long = 0}