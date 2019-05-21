package com.example.biblioteca.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "editorial_table")
data class Editorial(var name : String)
{@PrimaryKey(autoGenerate = true) var id : Long = 0}