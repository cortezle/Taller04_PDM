package com.example.biblioteca.database.repositories

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.biblioteca.database.daos.*
import com.example.biblioteca.database.entities.*

class BookRepository(private val bookDao : BookDao, private val authorDao : AuthorDao, private val editorialDao: EditorialDao, private val tagDao: TagDao,
                     private val bookXAuthorDao: BookXAuthorDao, private val bookXEditorialDao: BookXEditorialDao, private val bookXTagDao: BookXTagDao) {

    val allBooks : LiveData<List<Book>> = bookDao.getAllBooks()
    val favoriteBooks : LiveData<List<Book>> = bookDao.getFavoriteBooks()
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

    fun getBookPerAuthor(authorId : Int):LiveData<List<Book>>{
     return   bookXAuthorDao.getBookPerAuthor(authorId)
    }

    fun getAuthorPerBook(bookId : String):LiveData<List<Author>>{
        return bookXAuthorDao.getAuthorPerBook(bookId)
    }


    fun getBookPerEditorial(editId : Int): LiveData<List<Book>>{
       return bookXEditorialDao.getBookPerEditorial(editId)
    }


    fun getEditorialPerBook(bookId : String): LiveData<List<Editorial>>{
        return bookXEditorialDao.getEditorialPerBook(bookId)
    }


    fun getBookPerTag(tagId : Int): LiveData<List<Book>>{
        return bookXTagDao.getBookPerTag(tagId)
    }


    fun getTagPerBook(bookId : String): LiveData<List<Tag>>{
        return bookXTagDao.getTagPerBook(bookId)
    }

    @WorkerThread
    suspend fun insertBookXEditorial(bookXEditorial: BookXEditorial){
        bookXEditorialDao.insert(bookXEditorial)
    }

    @WorkerThread
    suspend fun insertBookXTag(bookXTag : BookXTag){
        bookXTagDao.insert(bookXTag)
    }


    fun addFavorite(id : String){
        bookDao.addFavorite(id)
    }

    fun removeFavorite(id : String){
        bookDao.removeFavorite(id)
    }
}