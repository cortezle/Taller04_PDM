package com.example.biblioteca.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.biblioteca.database.entities.Tag

@Dao
interface TagDao {

    @Insert
    suspend fun insert(tag : Tag)

    @Query("SELECT * FROM tag_table ORDER BY idTag")
    fun getAllTag() : List<Tag>

}