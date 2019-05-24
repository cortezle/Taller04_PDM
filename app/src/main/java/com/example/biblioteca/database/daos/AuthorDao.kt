package com.example.biblioteca.database.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.biblioteca.database.entities.Author
import com.example.biblioteca.database.entities.Tag

@Dao
interface AuthorDao {

    @Insert
    suspend fun insert(author : Author)

    @Query("SELECT * FROM author_table ORDER BY idAuthor")
    fun getAllAuthor() : LiveData<List<Author>>
}