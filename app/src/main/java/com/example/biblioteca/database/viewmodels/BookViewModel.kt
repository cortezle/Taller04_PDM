package com.example.biblioteca.database.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.biblioteca.database.entities.Author
import com.example.biblioteca.database.entities.Book
import com.example.biblioteca.database.entities.Editorial
import com.example.biblioteca.database.entities.Tag
import com.example.biblioteca.database.repositories.BookRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BookViewModel(application: Application) : AndroidViewModel(application){

    private val repository : BookRepository
    val allBooks : LiveData<List<Book>>

    init {
        val booksDao = BookRoomDatabase.getDatabase(application).bookDao()
        repository = BookRepository(booksDao)
        allBooks = repository.allBooks
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

}