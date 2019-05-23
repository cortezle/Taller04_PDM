package com.example.biblioteca.database.entities

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(tableName = "bookXAuthor_table",
        primaryKeys = arrayOf("bookId", "authorId"),
        foreignKeys = [ForeignKey(entity = Book::class, parentColumns = ["idBook"], childColumns = ["bookId"]),
                ForeignKey(entity = Author::class, parentColumns = ["idAuthor"], childColumns = ["authorId"])]
)
data class BookXAuthor (
    var bookId : Long,
    var authorId : Long
)