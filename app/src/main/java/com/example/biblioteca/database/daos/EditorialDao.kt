package com.example.biblioteca.database.daos

import androidx.room.Dao
import androidx.room.Insert
import com.example.biblioteca.database.entities.Editorial

@Dao
interface EditorialDao {

    @Insert
    suspend fun insert(editorial : Editorial)

}