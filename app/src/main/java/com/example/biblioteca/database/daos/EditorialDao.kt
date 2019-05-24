package com.example.biblioteca.database.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.biblioteca.database.entities.Editorial
import com.example.biblioteca.database.entities.Tag

@Dao
interface EditorialDao {

    @Insert
    suspend fun insert(editorial : Editorial)

    @Query("SELECT * FROM editorial_table ORDER BY idEditorial")
    fun getAllEditorial() : LiveData<List<Editorial>>
}