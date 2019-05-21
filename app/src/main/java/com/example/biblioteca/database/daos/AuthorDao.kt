package com.example.biblioteca.database.daos

import androidx.room.Dao
import androidx.room.Insert
import com.example.biblioteca.database.entities.Author

@Dao
interface AuthorDao {

    @Insert
    suspend fun insert(author : Author)

}