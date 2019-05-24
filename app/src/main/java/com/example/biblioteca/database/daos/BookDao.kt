package com.example.biblioteca.database.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.biblioteca.database.entities.Book

@Dao
interface BookDao {

    @Query("SELECT * FROM book_table ORDER BY title ASC")
    fun getAllBooks() : LiveData<List<Book>>

    @Insert
    suspend fun insert(book : Book)

    @Query("UPDATE book_table SET favorite = 1 WHERE idBook = :id")
    fun addFavorite(id : Long)

    @Query("UPDATE book_table SET favorite = 0 WHERE idBook = :id")
    fun removeFavorite(id : Long)

    @Query("DELETE FROM book_table")
    fun deleteAll()
}