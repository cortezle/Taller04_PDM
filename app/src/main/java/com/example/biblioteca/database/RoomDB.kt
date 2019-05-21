package com.example.biblioteca.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.biblioteca.database.daos.AuthorDao
import com.example.biblioteca.database.daos.BookDao
import com.example.biblioteca.database.daos.EditorialDao
import com.example.biblioteca.database.daos.TagDao
import com.example.biblioteca.database.entities.Author
import com.example.biblioteca.database.entities.Book
import com.example.biblioteca.database.entities.Editorial
import com.example.biblioteca.database.entities.Tag
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
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

        fun getDatabase(context: Context, scope: CoroutineScope) : BookRoomDatabase{
            val tempInstance = INSTANCE

            if (tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BookRoomDatabase::class.java,
                    "Book_database"
                )
                    .addCallback(BookDatabaseCallback(scope))
                    .build()

                INSTANCE = instance
                return instance
            }
        }
    }

    private class BookDatabaseCallback(private val scope: CoroutineScope) : RoomDatabase.Callback(){
        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let {database ->
                scope.launch(Dispatchers.IO){

                    populateDatabase(database.bookDao(),database.editorialDao(),database.tagDao(),database.authorDao())
                }
            }
        }

        fun populateDatabase(bookDao: BookDao,editorialDao: EditorialDao,tagDao: TagDao,authorDao: AuthorDao){

            // FILL THE DATABASE!!!

        }
    }







}