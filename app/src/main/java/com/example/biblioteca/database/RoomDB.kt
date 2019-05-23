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

        suspend fun populateDatabase(bookDao: BookDao, editorialDao: EditorialDao, tagDao: TagDao, authorDao: AuthorDao){
            bookDao.deleteAll()

            var book1 = Book("El extrangero", "Albert Camus", "caratula.jpg", 1, "Mateu Cromo S.A.",
                "Meursault recibe un mañana un telegrama en el que se le notifica la muerte de su madre. " +
                        "En una playa de Argelia mata inesperadamente a un hombre y es sometido a juicio absurdo." +
                        "¿Cuales son las razones por las que vale la pena nacer, morir y matar? la historia de Meursault " +
                        "reaviva nuestro intento por dar respuesta a estas preguntas",
                "Nacer, Morir, Matar", "84-89669-45-7", 0)
            var editorial1 = Editorial("Mateu Cromo S.A.")
            var tag11 = Tag("Morir")
            var tag12 = Tag("Nacer")
            var tag13 = Tag("Matar")
            var author1 = Author("Albert Camus")

            bookDao.insert(book1)
            tagDao.insert(tag11)
            tagDao.insert(tag12)
            tagDao.insert(tag13)
            authorDao.insert(author1)


            var book2 = Book("The Shining","Stephen King","1",1,
                "Random House", "Jack Torrence's new job at the Overlook Hotel is the perfect chance for a fresh start. As the off-season caretaker at the atmospheric old hotel, he'll have plenty of time to spend reconnecting with his family," +
                        " all though the hotel keeps some secrets to the one's who shine.","CD",
                "978-0-385-12167-5",0)
            var tag21 = Tag("Morir")
            var author2 = Author("Stephen King")

            bookDao.insert(book2)
            tagDao.insert(tag21)
            authorDao.insert(author2)
        }
    }







}