package com.example.biblioteca.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "book_table")
data class Book(
    var title : String,
    var cover : String,
    var edition : Int,
    var synopsis : String,
    @PrimaryKey var idBook : String,
    var favorite : Int
)