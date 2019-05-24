package com.example.biblioteca.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "author_table")
data class Author(
    @PrimaryKey var idAuthor : Int,
    var name : String)
