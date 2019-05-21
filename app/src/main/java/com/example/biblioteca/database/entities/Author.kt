package com.example.biblioteca.database.entities

import androidx.room.Entity

@Entity(tableName = "author_table")
data class Author(var name : String)