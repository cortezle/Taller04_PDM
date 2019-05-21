package com.example.biblioteca.database.daos

import androidx.room.Dao
import androidx.room.Insert
import com.example.biblioteca.database.entities.Tag

@Dao
interface TagDao {

    @Insert
    suspend fun insert(tag : Tag)

}