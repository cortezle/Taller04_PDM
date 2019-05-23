package com.example.biblioteca.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tag_table")
data class Tag(var word : String)
{@PrimaryKey(autoGenerate = true) var idTag : Long = 0}