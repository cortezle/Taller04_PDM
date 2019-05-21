package com.example.biblioteca.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.biblioteca.database.daos.AuthorDao
import com.example.biblioteca.database.daos.BookDao
import com.example.biblioteca.database.daos.EditorialDao
import com.example.biblioteca.database.daos.TagDao
import com.example.biblioteca.database.entities.Author
import com.example.biblioteca.database.entities.Book
import com.example.biblioteca.database.entities.Editorial
import com.example.biblioteca.database.entities.Tag
import java.security.AccessControlContext

@Database(entities = arrayOf(Book::class,
    Author::class,
    Editorial::class,
    Tag::class),
    version = 1)
public abstract class BookRoomDatabase : RoomDatabase(){

    abstract fun bookDao() : BookDao
    abstract fun authorDao() : AuthorDao
    abstract fun editorialDao() : EditorialDao
    abstract fun tagDao() : TagDao

    companion object{
        @Volatile

        private var INSTANCE : BookRoomDatabase? = null

        fun getDatabase(context: Context) : BookRoomDatabase{
            val tempInstance = INSTANCE

            if (tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BookRoomDatabase::class.java,
                    "Book_database"
                ).build()

                INSTANCE = instance
                return instance
            }
        }
    }




}