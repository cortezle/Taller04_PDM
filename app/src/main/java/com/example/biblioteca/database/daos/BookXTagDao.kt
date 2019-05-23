package com.example.biblioteca.database.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.biblioteca.database.entities.Book
import com.example.biblioteca.database.entities.BookXTag
import com.example.biblioteca.database.entities.Tag

@Dao
interface BookXTagDao {

    @Insert
    fun insert(bookXtag : BookXTag)

    @Query("SELECT * FROM book_table INNER JOIN bookXTag_table ON book_table.idBook = bookXTag_table.bookId WHERE bookXTag_table.tagId = :tagId")
    fun getBookPerTag(tagId : Long) : LiveData<List<Book>>

    @Query("SELECT * FROM tag_table INNER JOIN bookXTag_table ON tag_table.idTag = bookXTag_table.tagId WHERE bookXTag_table.bookId = :bookId")
    fun getTagPerBook(bookId : Long) :  LiveData<List<Tag>>

}