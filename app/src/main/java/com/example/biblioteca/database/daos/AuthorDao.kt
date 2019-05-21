package com.example.biblioteca.database.daos

import androidx.room.Insert
import com.example.biblioteca.database.entities.Author

interface AuthorDao {

    @Insert
    suspend fun insert(author : Author)

}