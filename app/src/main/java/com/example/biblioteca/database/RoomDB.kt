package com.example.biblioteca.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.biblioteca.database.daos.*
import com.example.biblioteca.database.entities.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.security.AccessControlContext

@Database(entities = arrayOf(Book::class,
    Author::class,
    Editorial::class,
    Tag::class,
    BookXAuthor::class,
    BookXEditorial::class,
    BookXTag::class),
    version = 1)
public abstract class BookRoomDatabase : RoomDatabase(){

    abstract fun bookDao() : BookDao
    abstract fun authorDao() : AuthorDao
    abstract fun editorialDao() : EditorialDao
    abstract fun tagDao() : TagDao
    abstract fun bookXAuthorDao() : BookXAuthorDao
    abstract fun bookXEditorialDao() : BookXEditorialDao
    abstract fun bookXTagDao() : BookXTagDao

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

                    populateDatabase(database.bookDao(),database.editorialDao(),database.tagDao(),database.authorDao(), database.bookXAuthorDao(), database.bookXEditorialDao(), database.bookXTagDao())
                }
            }
        }

        suspend fun populateDatabase(bookDao: BookDao, editorialDao: EditorialDao, tagDao: TagDao, authorDao: AuthorDao,
                                     bookXAuthorDao: BookXAuthorDao, bookXEditorialDao: BookXEditorialDao, bookXTagDao: BookXTagDao){
            bookDao.deleteAll()

            var editorial1 = Editorial("Mateu Cromo S.A.")
            var tag11 = Tag("Morir")
            var tag12 = Tag("Nacer")
            var tag13 = Tag("Matar")
            var author1 = Author("Albert Camus")

            var book1 = Book("El extrangero", author1.name, "caratula.jpg", 1, editorial1.name,
                "Meursault recibe un mañana un telegrama en el que se le notifica la muerte de su madre. " +
                        "En una playa de Argelia mata inesperadamente a un hombre y es sometido a juicio absurdo." +
                        "¿Cuales son las razones por las que vale la pena nacer, morir y matar? la historia de Meursault " +
                        "reaviva nuestro intento por dar respuesta a estas preguntas",
                tag11.word + ", " + tag12.word + ", " + tag13.word, "84-89669-45-7", 0)

            bookDao.insert(book1)
            authorDao.insert(author1)
            editorialDao.insert(editorial1)
            tagDao.insert(tag11)
            tagDao.insert(tag12)
            tagDao.insert(tag13)

            /*bookXAuthorDao.insert(BookXAuthor(book1.idBook, author1.idAuthor))
            bookXEditorialDao.insert(BookXEditorial(book1.idBook, editorial1.idEditorial))
            bookXTagDao.insert(BookXTag(book1.idBook, tag11.idTag))
            bookXTagDao.insert(BookXTag(book1.idBook, tag12.idTag))
            bookXTagDao.insert(BookXTag(book1.idBook, tag13.idTag))*/
        }

        /*fun image(filepath : ) : String{

        }*/

    }







}