package com.example.biblioteca.database.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.biblioteca.database.entities.Author
import com.example.biblioteca.database.entities.Book
import com.example.biblioteca.database.entities.BookXAuthor

@Dao
interface BookXAuthorDao {

    @Insert
    suspend fun insert(bookXauthor : BookXAuthor)

    @Query("SELECT * FROM book_table INNER JOIN bookXAuthor_table ON book_table.idBook = bookXAuthor_table.bookId WHERE bookXAuthor_table.authorId = :authorId")
    fun getBookPerAuthor(authorId : Int) : LiveData<List<Book>>

    @Query("SELECT * FROM author_table INNER JOIN bookXAuthor_table ON author_table.idAuthor = bookXAuthor_table.authorId WHERE bookXAuthor_table.bookId = :bookId")
    fun getAuthorPerBook(bookId : String) : LiveData<List<Author>>

}