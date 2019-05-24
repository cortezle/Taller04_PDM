package com.example.biblioteca.database.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.biblioteca.database.BookRoomDatabase
import com.example.biblioteca.database.entities.*
import com.example.biblioteca.database.repositories.BookRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BookViewModel(application: Application) : AndroidViewModel(application){

    private val repository : BookRepository
    val allBooks : LiveData<List<Book>>
    val allAuthors : LiveData<List<Author>>
    val allEditorials : LiveData<List<Editorial>>
    val allTags : LiveData<List<Tag>>

    init {
        val booksDao = BookRoomDatabase.getDatabase(application,viewModelScope).bookDao()
        val editorialDao = BookRoomDatabase.getDatabase(application,viewModelScope).editorialDao()
        val tagDao = BookRoomDatabase.getDatabase(application,viewModelScope).tagDao()
        val authorDao = BookRoomDatabase.getDatabase(application,viewModelScope).authorDao()
        val bookXAuthorDao = BookRoomDatabase.getDatabase(application, viewModelScope).bookXAuthorDao()
        val bookXEditorialDao = BookRoomDatabase.getDatabase(application, viewModelScope).bookXEditorialDao()
        val bookXTagDao = BookRoomDatabase.getDatabase(application, viewModelScope).bookXTagDao()

        repository = BookRepository(booksDao,authorDao,editorialDao,tagDao, bookXAuthorDao, bookXEditorialDao, bookXTagDao)

        allBooks = repository.allBooks
        allAuthors = repository.allAuthors
        allEditorials = repository.allEditorials
        allTags = repository.allTags
    }

    fun insertAuthor(author: Author) = viewModelScope.launch(Dispatchers.IO){
        repository.insertAuthor(author)
    }

    fun insertEditorial(editorial: Editorial) = viewModelScope.launch(Dispatchers.IO){
        repository.insertEditorial(editorial)
    }

    fun insertBook(book: Book) = viewModelScope.launch(Dispatchers.IO){
        repository.insertBook(book)
    }

    fun insertTag(tag: Tag) = viewModelScope.launch(Dispatchers.IO){
        repository.insertTag(tag)
    }

    fun insertBookXAuthor(bookXAuthor: BookXAuthor) = viewModelScope.launch(Dispatchers.IO){
        repository.insertBookXAuthor(bookXAuthor)
    }

    fun insertBookXEditorial(bookXEditorial: BookXEditorial) = viewModelScope.launch(Dispatchers.IO){
        repository.insertBookXEditorial(bookXEditorial)
    }

    fun insertBookXTag(bookXTag: BookXTag) = viewModelScope.launch(Dispatchers.IO){
        repository.insertBookXTag(bookXTag)
    }

    fun getBookPerAuthor(authorId : Int) = viewModelScope.launch(Dispatchers.IO){
        repository.getBookPerAuthor(authorId)
    }

    fun getAuthorPerBook(bookId : String) = viewModelScope.launch(Dispatchers.IO){
        repository.getAuthorPerBook(bookId)
    }

    fun getBookPerEditorial(editId : Int) = viewModelScope.launch(Dispatchers.IO){
        repository.getBookPerEditorial(editId)
    }

    fun getEditorialPerBook(bookId : String) = viewModelScope.launch(Dispatchers.IO){
        repository.getEditorialPerBook(bookId)
    }

    fun getBookPerTag(tagId : Int) = viewModelScope.launch(Dispatchers.IO){
        repository.getBookPerTag(tagId)
    }

    fun getTagPerBook(bookId : String) = viewModelScope.launch(Dispatchers.IO){
        repository.getTagPerBook(bookId)
    }

    fun addFavorite(idBook : String) = viewModelScope.launch(Dispatchers.IO){
        repository.addFavorite(idBook)
    }

    fun removeFavorite(idBook : String) = viewModelScope.launch(Dispatchers.IO){
        repository.removeFavorite(idBook)
    }

}