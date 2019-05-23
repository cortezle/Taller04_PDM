package com.example.biblioteca.database.entities

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(tableName = "bookXEditorial_table",
    primaryKeys = arrayOf("bookId", "editorialId"),
    foreignKeys = [ForeignKey(entity = Book::class, parentColumns = ["idBook"], childColumns = ["bookId"]),
        ForeignKey(entity = Editorial::class, parentColumns = ["idEditorial"], childColumns = ["editorialId"])])
data class BookXEditorial(
    var bookId : Long,
    var editorialId : Long
)