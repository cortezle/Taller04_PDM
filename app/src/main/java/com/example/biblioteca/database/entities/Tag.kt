package com.example.biblioteca.database.entities

import androidx.room.Entity

@Entity(tableName = "tag_table")
data class Tag(var word : String)