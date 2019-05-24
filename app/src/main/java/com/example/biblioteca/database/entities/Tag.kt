package com.example.biblioteca.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tag_table")
data class Tag(
    @PrimaryKey var idTag : Int,
    var word : String)