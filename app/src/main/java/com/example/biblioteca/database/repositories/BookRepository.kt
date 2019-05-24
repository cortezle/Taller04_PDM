package com.example.biblioteca.database.repositories

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.biblioteca.database.daos.*
import com.example.biblioteca.database.entities.*

class BookRepository(private val bookDao : BookDao, private val authorDao : AuthorDao, private val editorialDao: EditorialDao, private val tagDao: TagDao,
                     private val bookXAuthorDao: BookXAuthorDao, private val bookXEditorialDao: BookXEditorialDao, private val bookXTagDao: BookXTagDao) {

    val allBooks : LiveData<List<Book>> = bookDao.getAllBooks()
    val allAuthors : LiveData<List<Author>> = authorDao.getAllAuthor()
    val allEditorials : LiveData<List<Editorial>> = editorialDao.getAllEditorial()
    val allTags : LiveData<List<Tag>> = tagDao.getAllTag()

    @WorkerThread
    suspend fun insertBook(book : Book){
        bookDao.insert(book)
    }

    @WorkerThread
    suspend fun insertAuthor(author: Author){
        authorDao.insert(author)
    }

    @WorkerThread
    suspend fun insertEditorial(editorial: Editorial){
        editorialDao.insert(editorial)
    }

    @WorkerThread
    suspend fun insertTag(tag: Tag){
        tagDao.insert(tag)
    }

    @WorkerThread
    suspend fun insertBookXAuthor(bookXAuthor: BookXAuthor){
        bookXAuthorDao.insert(bookXAuthor)
    }

    @WorkerThread
    fun getBookPerAuthor(authorId : Int){
        bookXAuthorDao.getBookPerAuthor(authorId)
    }

    @WorkerThread
    fun getAuthorPerBook(bookId : String){
        bookXAuthorDao.getAuthorPerBook(bookId)
    }

    @WorkerThread
    fun getBookPerEditorial(editId : Int){
        bookXEditorialDao.getBookPerEditorial(editId)
    }

    @WorkerThread
    fun getEditorialPerBook(bookId : String){
        bookXEditorialDao.getEditorialPerBook(bookId)
    }

    @WorkerThread
    fun getBookPerTag(tagId : Int){
        bookXTagDao.getBookPerTag(tagId)
    }

    @WorkerThread
    fun getTagPerBook(bookId : String){
        bookXTagDao.getTagPerBook(bookId)
    }

    @WorkerThread
    suspend fun insertBookXEditorial(bookXEditorial: BookXEditorial){
        bookXEditorialDao.insert(bookXEditorial)
    }

    @WorkerThread
    suspend fun insertBookXTag(bookXTag : BookXTag){
        bookXTagDao.insert(bookXTag)
    }

    @WorkerThread
    suspend fun addFavorite(id : String){
        bookDao.addFavorite(id)
    }

    @WorkerThread
    suspend fun removeFavorite(id : String){
        bookDao.removeFavorite(id)
    }
}