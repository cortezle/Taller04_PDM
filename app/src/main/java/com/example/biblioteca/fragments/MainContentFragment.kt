package com.example.biblioteca.fragments

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide

import com.example.biblioteca.R
import com.example.biblioteca.database.entities.Author
import com.example.biblioteca.database.entities.Book
import com.example.biblioteca.database.entities.Editorial
import com.example.biblioteca.database.entities.Tag
import com.example.biblioteca.database.viewmodels.BookViewModel
import com.example.biblioteca.utils.AppConstants
import kotlinx.android.synthetic.main.fragment_main_content.*
import kotlinx.android.synthetic.main.fragment_main_content.view.*
import java.util.ArrayList

class MainContentFragment : Fragment() {

    lateinit var book : Book
    lateinit var bookViewModel: BookViewModel

    companion object{
        fun newInstance (book: Book):MainContentFragment{
            val newFragment = MainContentFragment()
            newFragment.book = book
            return  newFragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_main_content,container,false)

        bookViewModel = ViewModelProviders.of(this).get(BookViewModel::class.java)

        if(savedInstanceState != null) book = savedInstanceState.getParcelable(AppConstants.TEXT_KEY_BOOK)

        bindData(view)
        return view
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(AppConstants.TEXT_KEY_BOOK, book)
    }

    fun bindData(view: View) {
        view.textView_title.text = book.title

        view.textView_Author.text = book.author
        view.textView_Edition.text = book.edition.toString()
        view.textView_Editorial.text = book.editorial
        view.textView_Isbn.text = book.idBook
        view.textView_Synopsis.text = book.synopsis
        view.textView_Tags.text = book.tags

        view.button_fav.setBackgroundColor(Color.TRANSPARENT)

        if(book.favorite==0){
            view.button_fav.setImageResource(R.drawable.ic_star_border_black_24dp)
        }else{
            view.button_fav.setImageResource(R.drawable.ic_star_amarillo_24dp)
        }

        view.button_fav.setOnClickListener {
            if(book.favorite==0){
                book.favorite=1
                bookViewModel.addFavorite(book.idBook)
                button_fav.setImageResource(R.drawable.ic_star_amarillo_24dp)


            }else{
                book.favorite=0
                bookViewModel.removeFavorite(book.idBook)
                button_fav.setImageResource(R.drawable.ic_star_border_black_24dp)
            }
        }

        Glide.with(view).load(book.cover)
            .placeholder(R.drawable.ic_launcher_background)
            .into(view.imageView_book)
    }

    /*fun getAuthor(bookId : String): String{
        val arrayAuthors : List<Author>? = bookViewModel.getAuthorPerBook(bookId).value
        var cadena = ""
        arrayAuthors?.forEach { author : Author ->
            cadena = if(author.name.isEmpty()){
                "There are not authors"
            }else{
                author.name + " "
            }
        }
        return cadena
    }

    fun getEditorial(bookId: String): String{
        val arrayEditorials = bookViewModel.getEditorialPerBook(bookId).value?:ArrayList()
        var cadena = ""
        arrayEditorials.forEach { editorial : Editorial ->
            cadena = if(editorial.name.isEmpty()){
                "There are not authors"
            }else{
                editorial.name + " "
            }
        }
        return cadena
    }

    fun getTag(bookId: String): String{
        val arrayTags = bookViewModel.getTagPerBook(bookId).value?:ArrayList()
        var cadena = ""
        arrayTags.forEach { tag : Tag ->
            cadena = if(tag.word.isEmpty()){
                "There are not authors"
            }else{
                tag.word + " "
            }
        }
        return cadena
    }*/

}
