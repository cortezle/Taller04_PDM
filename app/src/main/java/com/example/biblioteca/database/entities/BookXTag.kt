package com.example.biblioteca.database.entities

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(tableName = "bookXTag_table",
    primaryKeys = arrayOf("bookId", "tagId"),
    foreignKeys = [ForeignKey(entity = Book::class, parentColumns = ["idBook"], childColumns = ["bookId"]),
        ForeignKey(entity = Tag::class, parentColumns = ["idTag"], childColumns = ["tagId"])])
data class BookXTag(
    var bookId : String,
    var tagId : Int
)