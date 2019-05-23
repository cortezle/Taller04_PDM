package com.example.biblioteca.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "book_table")
data class Book(
    @ColumnInfo(name = "title") var title : String,
    @ColumnInfo(name = "author") var authors : String,
    @ColumnInfo(name = "cover") var cover : String,
    @ColumnInfo(name = "edition") var edition : Int,
    @ColumnInfo(name = "editorial") var editorial : String,
    @ColumnInfo(name = "synopsis") var synopsis : String,
    @ColumnInfo(name = "tags") var tags : String,
    @ColumnInfo(name = "isbn") var isbn : String,
    @ColumnInfo(name = "favorite") var favorite : Int
){@PrimaryKey(autoGenerate = true) var idBook : Long = 0}