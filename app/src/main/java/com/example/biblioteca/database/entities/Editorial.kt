package com.example.biblioteca.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "editorial_table")
data class Editorial(
    @PrimaryKey var idEditorial : Int,
    var name : String)