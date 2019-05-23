package com.example.biblioteca.database.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.biblioteca.database.entities.Book
import com.example.biblioteca.database.entities.BookXEditorial
import com.example.biblioteca.database.entities.Editorial

@Dao
interface BookXEditorialDao {

    @Insert
    fun insert(bookXEditorial: BookXEditorial)

    @Query("SELECT * FROM book_table INNER JOIN bookXEditorial_table ON book_table.idBook = bookXEditorial_table.bookId WHERE bookXEditorial_table.editorialId = :editorialId")
    fun getBookPerEditorial(editorialId : Long) : LiveData<List<Book>>

    @Query("SELECT * FROM editorial_table INNER JOIN bookXEditorial_table ON editorial_table.idEditorial = bookXEditorial_table.editorialId WHERE bookXEditorial_table.bookId = :bookId")
    fun getEditorialPerBook(bookId : Long) : LiveData<List<Editorial>>

}