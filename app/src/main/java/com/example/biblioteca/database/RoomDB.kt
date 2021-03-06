package com.example.biblioteca.database

import android.R
import android.content.Context
import android.util.Log
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
                    if(database.bookDao().verify().size == 0) populateDatabase(database.bookDao(),database.editorialDao(),database.tagDao(),database.authorDao(), database.bookXAuthorDao(), database.bookXEditorialDao(), database.bookXTagDao())

                }
            }
        }

        suspend fun populateDatabase(bookDao: BookDao, editorialDao: EditorialDao, tagDao: TagDao, authorDao: AuthorDao,
                                     bookXAuthorDao: BookXAuthorDao, bookXEditorialDao: BookXEditorialDao, bookXTagDao: BookXTagDao){

            var book1 = Book("El extranjero", "Albert Camus", "https://static2planetadelibroscom.cdnstatics.com/usuaris/libros/fotos/9/original/portada_el-extranjero_albert-camus_201505261225.jpg", 1, "Mateu Cromo S.A.",
                "Meursault recibe un mañana un telegrama en el que se le notifica la muerte de su madre. " +
                        "En una playa de Argelia mata inesperadamente a un hombre y es sometido a juicio absurdo." +
                        "¿Cuales son las razones por las que vale la pena nacer, morir y matar? la historia de Meursault " +
                        "reaviva nuestro intento por dar respuesta a estas preguntas", "84-89669-45-7",
                    "Morir, Nacer, Matar", 0)
            bookDao.insert(book1)
            /*var author1 = "Albert Camus"
            var editorial1 = "Mateu Cromo S.A."
            var tag11 = "Morir"
            var tag12 = "Nacer"
            var tag13 = "Matar"

            insertMain(bookDao, authorDao, editorialDao, tagDao, book1, arrayOf(author1), arrayOf(editorial1), arrayOf(tag11, tag12, tag13), bookXAuthorDao, bookXEditorialDao, bookXTagDao)*/

            var book2 = Book("The Shining", "Stephen King", "http://t0.gstatic.com/images?q=tbn:ANd9GcR1uTHLLpBhlXQEVDzar5kTUuDxo99jueJWXgVeTaQN1N6N58Tq", 1, "Random House",
                    "Jack Torrence's new job at the Overlook Hotel is the perfect chance for a fresh start. " +
                    "As the off-season caretaker at the atmospheric old hotel, he'll have plenty of time to spend reconnecting with his family, all though the hotel keeps some " +
                    "secrets to the one's who shine.", "978-0-385-12167-5", "Morir", 0)
            bookDao.insert(book2)
            /*var author2 = Author(1, "Stephen King")
            var editorial2 = Editorial(1, "Random House")
            var tag21 = "Morir"

            insertMain(bookDao, authorDao, editorialDao, tagDao, book2, arrayOf(author2), arrayOf(editorial2), arrayOf(tag21), bookXAuthorDao, bookXEditorialDao, bookXTagDao)*/

            var book3 = Book("Harry Potter and the chamber of secrets", "J.K. Rowling", "https://static2planetadelibroscom.cdnstatics.com/usuaris/libros/fotos/275/original/portada_los-tesoros-de-harry-potter-edicion-actualizada_aa-vv_201807191136.jpg", 1, "Scholastic",
                        "The Dursleys were so mean and hideous that summer that all Harry Potter wanted was to get back to the Hogwarts School for Witchcraft and Wizardry" +
                        ". But just as he's packing his bags, Harry receives a warning from a strange, impish creature named Dobby who says that if Harry Potter returns to Hogwarts," +
                        " disaster will strike","950-0-42068-6", "Adventure", 0)
            bookDao.insert(book3)
            /*var author3 = "J.K. Rowling"
            var editorial3 = "Scholastic"
            var tag31 = "Adventure"

            insertMain(bookDao, authorDao, editorialDao, tagDao, book3, arrayOf(author3), arrayOf(editorial3), arrayOf(tag31,tag32), bookXAuthorDao, bookXEditorialDao, bookXTagDao)*/

            var book4 = Book("El tren de las 4.50", "Agatha Christie", "https://static2planetadelibroscom.cdnstatics.com/usuaris/libros/fotos/271/original/portada_el-tren-de-las-450_agatha-christie_201805011904.jpg", 1,
                "Booket",
                "Por un instante, los dos trenes circularon paralelos. En ese preciso momento, Elspeth McGillicuddy presenció un asesinato. Desde su vagón vio con impotencia como en el otro tren un hombre agarraba sin piedad el cuello " +
                        "de una mujer hasta estrangularla. Después el tren se alejó.","978-84-670-5299-2", "Asesinato, tren", 0)
            bookDao.insert(book4)
            /*var author4 = "J.D Salinger"
            var editorial4 = "Alianza"
            var tag41 = "Christmast"
            var tag42 = "Hospital"

            insertMain(bookDao, authorDao, editorialDao, tagDao, book4, arrayOf(author4), arrayOf(editorial4), arrayOf(tag41,tag42), bookXAuthorDao, bookXEditorialDao, bookXTagDao)*/

            var book5 = Book("Asesinato en el oriente express", "Agatha Christie", "https://static2planetadelibroscom.cdnstatics.com/usuaris/libros/fotos/201/original/portada_asesinato-en-el-orient-express_agatha-christie_201505291004.jpg", 1, "Collins Crime Club, Pan Books",
                "When a murder occurs on the train on which he's travelling, celebrated detective Hercule Poirot is recruited to solve the case.",
                "9780007527502", "Misterio, Asesinato", 0)
            bookDao.insert(book5)
            /*var author5 = "Agatha Christie"
            var editorial5 = "Collins Crime Club"
            var editorial51 = "Pan Books"
            var tag51 = "Misterio"
            var tag52 = "Asesinato"

            insertMain(bookDao, authorDao, editorialDao, tagDao, book5, arrayOf(author5), arrayOf(editorial5, editorial51), arrayOf(tag51,tag52), bookXAuthorDao, bookXEditorialDao, bookXTagDao)*/

            var book6 = Book("El Alquimista", "Paulo Cohelo", "https://static2planetadelibroscom.cdnstatics.com/usuaris/libros/fotos/120/original/el-alquimista_9788408130451.jpg", 1, "Planeta",
                        "Poderosa, sencilla, sabia e inspiradora, ésta es la historia de Santiago, un joven pastor andaluz que viaja desde su tierra natal hacia el " +
                        "desierto egipcio en busca de un tesoro oculto en las pirámides. Nadie sabe lo que" +
                        " contiene el tesoro, ni si Santiago será capaz de superar los obstáculos del camino. ",
                        "978-84-08-14475-5", "Inspirador, Suenio", 0)
            bookDao.insert(book6)
            /*var author6 = "Paulo Cohelo"
            var editorial6 = "Planeta"
            var tag61 = "Inspirador"
            var tag62 = "Suenio"

            insertMain(bookDao, authorDao, editorialDao, tagDao, book6, arrayOf(author6), arrayOf(editorial6), arrayOf(tag61,tag62), bookXAuthorDao, bookXEditorialDao, bookXTagDao)*/

            var book7 = Book("La espia", "Paulo Cohelo", "https://static2planetadelibroscom.cdnstatics.com/usuaris/libros/fotos/220/original/portada_la-espia_paulo-coelho_201608021206.jpg", 1, "Planeta",
                        "Paulo Coelho ahonda de forma magistral en la vida de una de las mujeres más fascinantes y desconocidas " +
                        "de la Historia. Sensual, fuerte y contradictoria, Mata Hari se ha convertido en un icono " +
                        "por enfrentarse a los cánones de su época y luchar por ser una mujer independiente y libre en un mundo convulso.",
                        "978-84-08-16180-6", "Primera guerra mundial, Indomable, Mujer", 0)
            bookDao.insert(book7)
            /*var author7 = "Paulo Cohelo"
            var editorial7 = "Planeta"
            var tag71 = "Primera guerra mundial"
            var tag72 = "Indomable"
            var tag73 = "Mujer"

            insertMain(bookDao, authorDao, editorialDao, tagDao, book7, arrayOf(author7), arrayOf(editorial7), arrayOf(tag71,tag72,tag73), bookXAuthorDao, bookXEditorialDao, bookXTagDao)*/

            var book8 = Book("Cumbres Borroscosas", "Emily Bronte", "https://static2planetadelibroscom.cdnstatics.com/usuaris/libros/fotos/69/original/cumbres-borrascosas_9788408009092.jpg", 3, "Letras Universales",
                        "El ambiente cultural que se respiro en la casa de los bronte desde la epoca temprana condujo a la " +
                        "precocidad literaria de las 3 hermanas emily bronte a pesar de su corta existencia esta considerada" +
                        "como una artista consumida y muy por encima de su epoca",
                        "978-95-376-0835-8", "Amor, Epoca antigua, Mediaval", 0)
            bookDao.insert(book8)
            /*var author8 = "Emily Bronte"
            var editorial8 = "Letras Universales"
            var tag81 = "Amor"
            var tag82 = "Epoca antigua"
            var tag83 = "Mediaval"

            insertMain(bookDao, authorDao, editorialDao, tagDao, book8, arrayOf(author8), arrayOf(editorial8), arrayOf(tag81,tag82,tag83), bookXAuthorDao, bookXEditorialDao, bookXTagDao)*/

            var book9 = Book("Adulterio", "Paulo Cohelo", "https://static2planetadelibroscom.cdnstatics.com/usuaris/libros/fotos/163/original/adulterio_9788408131625.jpg", 1, "Planeta",
                        "Linda está casada con un hombre rico, tienen dos hijos y la familia vive en una hermosa casa en Ginebra, Suiza. Trabaja en el periódico más importante del país," +
                        " es guapa, viste bien y tiene todo lo que se pueda desear. A ojos de todos, su vida es perfecta. ",
                        "978-84-08-13162-5", "Novela, Contemporaneo, Interesante",0)
            bookDao.insert(book9)
            /*var author9 = "Paulo Cohelo"
            var editorial9 = "Planeta"
            var tag9 = "Novela"
            var tag92 = "Contemporaneo"
            var tag93 = "Interesante"

            insertMain(bookDao, authorDao, editorialDao, tagDao, book9, arrayOf(author9), arrayOf(editorial9), arrayOf(tag9,tag92,tag93), bookXAuthorDao, bookXEditorialDao, bookXTagDao)*/

            var book10 = Book("Mi pecado", "Javier Moro", "https://static2planetadelibroscom.cdnstatics.com/usuaris/libros/fotos/292/original/portada_mi-pecado_javier-moro_201902062224.jpg", 1, "Booket",
                        "Conchita Montenegro desembarcó en Hollywood con apenas 19 años. Hermosa, inteligente e indómita, la sonora bofetada que propinó a un Clark Gable que se pasaba " +
                        "de listo nada más empezar su carrera la situó entre las principales estrellas del momento. ",
                        "978-84-670-5562-7","General, Contemporaneo, Novela", 0)
            bookDao.insert(book10)
            /*var author10 = "Javier Moro"
            var editorial_10 = "Booket"
            var tag_10 = "General"
            var tag_10_1 = "Contemporaneo"
            var tag10_2 = "Novela"

            insertMain(bookDao, authorDao, editorialDao, tagDao, book10, arrayOf(author10), arrayOf(editorial_10), arrayOf(tag_10,tag_10_1,tag10_2), bookXAuthorDao, bookXEditorialDao, bookXTagDao)*/

        }

        /*suspend fun insertMain(bookDao : BookDao, authorDao: AuthorDao, editorialDao: EditorialDao, tagDao: TagDao, book : Book, authors : Array<String>, editorials: Array<String>,
                               tags: Array<String>, bookXAuthorDao: BookXAuthorDao, bookXEditorialDao: BookXEditorialDao, bookXTagDao: BookXTagDao){
            bookDao.insert(book)

            var arrayAuthor = authorDao.getAllAuthor().value
            if(arrayAuthor != null){
                var idAuthor : Int
                if(arrayAuthor.size != 0) idAuthor = arrayAuthor.get(arrayAuthor.indexOf(arrayAuthor.get(arrayAuthor.size-1))).idAuthor
                else idAuthor = 0
                for(i : Int in 1..authors.size){
                    var author = Author(idAuthor + i, authors.get(i-1))
                    if(idAuthor != 0){
                        if(checkAuthors(author.name, arrayAuthor)){
                            authorDao.insert(author)
                            bookXAuthorDao.insert(BookXAuthor(book.idBook, author.idAuthor))
                        }
                    }
                }
            }else{
                var i = 0
                for(author : String in authors){
                    authorDao.insert(Author(i, author))
                    i++
                }
            }

            var arrayEditorial = editorialDao.getAllEditorial().value
            if(arrayEditorial != null){
                var idEditorial : Int
                if(arrayEditorial.size != 0) idEditorial = arrayEditorial.get(arrayEditorial.indexOf(arrayEditorial.get(arrayEditorial.size-1))).idEditorial
                else idEditorial = 0
                for(i : Int in 1..editorials.size){
                    var edit = Editorial(idEditorial + i, editorials.get(i-1))
                    if(idEditorial != 0){
                        if(checkEditorials(edit.name, arrayEditorial)){
                            editorialDao.insert(edit)
                            bookXEditorialDao.insert(BookXEditorial(book.idBook, edit.idEditorial))
                        }
                    }
                }
            }else{
                var i = 0
                for(edit : String in editorials){
                    editorialDao.insert(Editorial(i, edit))
                    i++
                }
            }

            var arrayTag = tagDao.getAllTag().value
            if(arrayTag != null){
                var idTag : Int
                if(arrayTag.size != 0) idTag = arrayTag.get(arrayTag.indexOf(arrayTag.get(arrayTag.size-1))).idTag
                else idTag = 0
                for(i : Int in 1..tags.size){
                    var tag = Tag(idTag+i, tags.get(i-1))
                    if(idTag != 0){
                        if(checkTags(tag.word, arrayTag)){
                            tagDao.insert(tag)
                            bookXTagDao.insert(BookXTag(book.idBook, tag.idTag))
                        }
                    }
                }
            }else{
                var i = 0
                for(tag : String in tags){
                    tagDao.insert(Tag(i, tag))
                    i++
                }
            }
        }

        fun checkAuthors(name : String, arrayAuthor: List<Author>) : Boolean{
            for(author : Author in arrayAuthor){
                if(name == author.name) return false
            }
            return true
        }

        fun checkEditorials(name : String, arrayEditorial: List<Editorial>) : Boolean{
            for(edit : Editorial in arrayEditorial){
                if(name == edit.name) return false
            }
            return true
        }

        fun checkTags(word : String, arrayTag: List<Tag>) : Boolean{
            for(tag : Tag in arrayTag){
                if(word == tag.word) return false
            }
            return true
        }*/
    }
}