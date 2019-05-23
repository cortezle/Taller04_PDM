package com.example.biblioteca.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "author_table")
data class Author(var name : String)
{@PrimaryKey(autoGenerate = true) var idAuthor : Long = 0}