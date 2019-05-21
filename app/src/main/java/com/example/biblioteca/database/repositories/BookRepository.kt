package com.example.biblioteca.database.repositories

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.biblioteca.database.daos.AuthorDao
import com.example.biblioteca.database.daos.BookDao
import com.example.biblioteca.database.daos.EditorialDao
import com.example.biblioteca.database.daos.TagDao
import com.example.biblioteca.database.entities.Author
import com.example.biblioteca.database.entities.Book
import com.example.biblioteca.database.entities.Editorial
import com.example.biblioteca.database.entities.Tag

class BookRepository(private val bookDao : BookDao, private val authorDao : AuthorDao, private val editorialDao: EditorialDao, private val tagDao: TagDao) {

    val allBooks : LiveData<List<Book>> = bookDao.getAllBooks()

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
    suspend fun addFavourite(id : Long){
        bookDao.addFavorite(id)
    }

    @WorkerThread
    suspend fun removeFavourite(id : Long){
        bookDao.removeFavorite(id)
    }
}