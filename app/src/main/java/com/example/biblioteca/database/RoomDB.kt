package com.example.biblioteca.database

import android.content.Context
import android.util.Range
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.biblioteca.database.daos.*
import com.example.biblioteca.database.entities.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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
                    if(database.authorDao().getAllAuthor().size == 0) populateDatabase(database.bookDao(),database.editorialDao(),database.tagDao(),database.authorDao(), database.bookXAuthorDao(), database.bookXEditorialDao(), database.bookXTagDao())
                }
            }
        }

        suspend fun populateDatabase(bookDao: BookDao, editorialDao: EditorialDao, tagDao: TagDao, authorDao: AuthorDao,
                                     bookXAuthorDao: BookXAuthorDao, bookXEditorialDao: BookXEditorialDao, bookXTagDao: BookXTagDao){

            var book1 = Book("El extranjero", "caratula.jpg", 1,
                "Meursault recibe un mañana un telegrama en el que se le notifica la muerte de su madre. " +
                        "En una playa de Argelia mata inesperadamente a un hombre y es sometido a juicio absurdo." +
                        "¿Cuales son las razones por las que vale la pena nacer, morir y matar? la historia de Meursault " +
                        "reaviva nuestro intento por dar respuesta a estas preguntas", "84-89669-45-7", 0)
            var author1 = "Albert Camus"
            var editorial1 = "Mateu Cromo S.A."
            var tag11 = "Morir"
            var tag12 = "Nacer"
            var tag13 = "Matar"


            insertMain(bookDao, authorDao, editorialDao, tagDao, book1, arrayOf(author1), arrayOf(editorial1), arrayOf(tag11, tag12, tag13), bookXAuthorDao, bookXEditorialDao, bookXTagDao)

            var book2 = Book("The Shining","caratula.jpg",1, "Jack Torrence's new job at the Overlook Hotel is the perfect chance for a fresh start. " +
                    "As the off-season caretaker at the atmospheric old hotel, he'll have plenty of time to spend reconnecting with his family, all though the hotel keeps some " +
                    "secrets to the one's who shine.", "978-0-385-12167-5",0)
            var author2 = "Stephen King"
            var editorial2 = "Random House"
            var tag21 = "Morir"

            insertMain(bookDao, authorDao, editorialDao, tagDao, book2, arrayOf(author2), arrayOf(editorial2), arrayOf(tag21), bookXAuthorDao, bookXEditorialDao, bookXTagDao)

            var book3 = Book("Harry Potter and the chamber of secrets", "caratula.jpg", 1,
                "The Dursleys were so mean and hideous that summer that all Harry Potter wanted was to get back to the Hogwarts School for Witchcraft and Wizardry" +
                        ". But just as he's packing his bags, Harry receives a warning from a strange, impish creature named Dobby who says that if Harry Potter returns to Hogwarts," +
                        " disaster will strike","950-0-42068-6",0)
            var author3 = "J.K. Rowling"
            var editorial3 = "Scholastic"
            var tag31 = "Adventure"
            var tag32 = ""

            insertMain(bookDao, authorDao, editorialDao, tagDao, book3, arrayOf(author3), arrayOf(editorial3), arrayOf(tag31,tag32), bookXAuthorDao, bookXEditorialDao, bookXTagDao)

            var book4 = Book("The catcher in the rye", "caratula.jpg", 1,
                "The Catcher in the Rye is set around the 1950s and is narrated by a young man named Holden Caulfield. Holden is not specific about his location while he’s telling the story," +
                        " but he makes it clear that he is undergoing treatment in a mental hospital or sanatorium. The events he narrates take place in the few days between the end of the fall school" +
                        " term and Christmas, when Holden is sixteen years old.","842-0-63409-3",0)
            var author4 = "J.D Salinger"
            var editorial4 = "Alianza"
            var tag41 = "Christmast"
            var tag42 = "hospital"

            insertMain(bookDao, authorDao, editorialDao, tagDao, book4, arrayOf(author4), arrayOf(editorial4), arrayOf(tag41,tag42), bookXAuthorDao, bookXEditorialDao, bookXTagDao)

            var book5 = Book("Asesinato en el oriente express", "caratula.jpg", 1,
                "When a murder occurs on the train on which he's travelling, celebrated detective Hercule Poirot is recruited to solve the case."," 9780007527502",0)
            var author5 = "Agatha Christie"
            var editorial5 = "Collins Crime Club"
            var editorial51 = "Pan Books"
            var tag51 = "Misterio"
            var tag52 = "Asesinato"

            insertMain(bookDao, authorDao, editorialDao, tagDao, book5, arrayOf(author5), arrayOf(editorial5, editorial51), arrayOf(tag51,tag52), bookXAuthorDao, bookXEditorialDao, bookXTagDao)

        }

        suspend fun insertMain(bookDao : BookDao, authorDao: AuthorDao, editorialDao: EditorialDao, tagDao: TagDao, book : Book, authors : Array<String>, editorials: Array<String>,
                               tags: Array<String>, bookXAuthorDao: BookXAuthorDao, bookXEditorialDao: BookXEditorialDao, bookXTagDao: BookXTagDao){
            bookDao.insert(book)

            var arrayAuthor = authorDao.getAllAuthor()
            var idAuthor : Int
            if(arrayAuthor.size != 0) idAuthor = arrayAuthor.get(arrayAuthor.indexOf(arrayAuthor.get(arrayAuthor.size-1))).idAuthor
            else idAuthor = 1
            for(i : Int in 1..authors.size){
                var author = Author(idAuthor + i, authors.get(i-1))
                authorDao.insert(author)
                bookXAuthorDao.insert(BookXAuthor(book.idBook, author.idAuthor))
            }

            var arrayEditorial = editorialDao.getAllEditorial()
            var idEditorial : Int
            if(arrayAuthor.size != 0) idEditorial = arrayEditorial.get(arrayEditorial.indexOf(arrayEditorial.get(arrayEditorial.size-1))).idEditorial
            else idEditorial = 1
            for(i : Int in 1..editorials.size){
                var edit = Editorial(idEditorial + i, editorials.get(i-1))
                editorialDao.insert(edit)
                bookXEditorialDao.insert(BookXEditorial(book.idBook, edit.idEditorial))
            }

            var arrayTag = tagDao.getAllTag()
            var idTag : Int
            if(arrayTag.size != 0) idTag = arrayTag.get(arrayTag.indexOf(arrayTag.get(arrayTag.size-1))).idTag
            else idTag = 1
            for(i : Int in 1..tags.size){
                var tag = Tag(idTag+i, tags.get(i-1))
                tagDao.insert(tag)
                bookXTagDao.insert(BookXTag(book.idBook, tag.idTag))
            }
        }
    }







}