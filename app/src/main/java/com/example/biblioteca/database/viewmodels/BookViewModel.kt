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
    val favoriteBooks : LiveData<List<Book>>
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
        favoriteBooks = repository.favoriteBooks
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

    fun getBookPerAuthor(authorId : Int):LiveData<List<Book>> = repository.getBookPerAuthor(authorId)


    fun getAuthorPerBook(bookId : String):LiveData<List<Author>> = repository.getAuthorPerBook(bookId)


    fun getBookPerEditorial(editId : Int): LiveData<List<Book>> = repository.getBookPerEditorial(editId)

    fun getEditorialPerBook(bookId : String): LiveData<List<Editorial>> = repository.getEditorialPerBook(bookId)

    fun getBookPerTag(tagId : Int): LiveData<List<Book>> = repository.getBookPerTag(tagId)


    fun getTagPerBook(bookId : String): LiveData<List<Tag>> = repository.getTagPerBook(bookId)

    fun addFavorite(idBook : String) = repository.addFavorite(idBook)

    fun removeFavorite(idBook : String) = repository.removeFavorite(idBook)


    fun insertListAuthor(authors : ArrayList<String>, book : Book){
        var arrayAuthor = allAuthors.value
        if(arrayAuthor != null){
            var idAuthor : Int
            if(arrayAuthor.size != 0) idAuthor = arrayAuthor.get(arrayAuthor.indexOf(arrayAuthor.get(arrayAuthor.size-1))).idAuthor
            else idAuthor = 0
            for(i : Int in 1..authors.size){
                var author = Author(idAuthor + i, authors.get(i-1))
                if(idAuthor != 0){
                    if(checkAuthors(author.name, arrayAuthor)){
                        insertAuthor(author)
                        insertBookXAuthor(BookXAuthor(book.idBook, author.idAuthor))
                    }
                }
            }
        }
    }

    fun insertListEditorial(edits : ArrayList<String>, book: Book){
        var arrayEditorial = allEditorials.value
        if(arrayEditorial != null){
            var idEditorial : Int
            if(arrayEditorial.size != 0) idEditorial = arrayEditorial.get(arrayEditorial.indexOf(arrayEditorial.get(arrayEditorial.size-1))).idEditorial
            else idEditorial = 0
            for(i : Int in 1..edits.size){
                var edit = Editorial(idEditorial + i, edits.get(i-1))
                if(idEditorial != 0){
                    if(checkEditorials(edit.name, arrayEditorial)){
                        insertEditorial(edit)
                        insertBookXEditorial(BookXEditorial(book.idBook, edit.idEditorial))
                    }
                }
            }
        }
    }

    fun insertListTag(tags : ArrayList<String>, book: Book){
        var arrayTag = allTags.value
        if(arrayTag != null){
            var idTag : Int
            if(arrayTag.size != 0) idTag = arrayTag.get(arrayTag.indexOf(arrayTag.get(arrayTag.size-1))).idTag
            else idTag = 0
            for(i : Int in 1..tags.size){
                var tag = Tag(idTag+i, tags.get(i-1))
                if(idTag != 0){
                    if(checkTags(tag.word, arrayTag)){
                        insertTag(tag)
                        insertBookXTag(BookXTag(book.idBook, tag.idTag))
                    }
                }
            }
        }
    }

    private fun checkAuthors(name : String, arrayAuthor: List<Author>) : Boolean{
        for(author : Author in arrayAuthor){
            if(name == author.name) return false
        }
        return true
    }

    private fun checkEditorials(name : String, arrayEditorial: List<Editorial>) : Boolean{
        for(edit : Editorial in arrayEditorial){
            if(name == edit.name) return false
        }
        return true
    }

    private fun checkTags(word : String, arrayTag: List<Tag>) : Boolean{
        for(tag : Tag in arrayTag){
            if(word == tag.word) return false
        }
        return true
    }
}