package com.example.biblioteca.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide

import com.example.biblioteca.R
import com.example.biblioteca.database.daos.BookDao
import com.example.biblioteca.database.entities.Author
import com.example.biblioteca.database.entities.Book
import com.example.biblioteca.database.entities.Editorial
import com.example.biblioteca.database.entities.Tag
import kotlinx.android.synthetic.main.fragment_main_content.view.*

class MainContentFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null

    lateinit var book : Book


    companion object{
        fun newInstance (book: Book):MainContentFragment{
            val newFragment = MainContentFragment()
            newFragment.book = book
            return  newFragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_main_content,container,false)

        bindData(view)

        return view
    }

    fun bindData(view: View) {
        view.textView_title.text = book.title

        //view.textView_Author.text = book.authors
        view.textView_Edition.text = book.edition.toString()
        //view.textView_Editorial.text = book.editorial
        view.textView_Isbn.text = book.idBook
        view.textView_Synopsis.text = book.synopsis
        //view.textView_Tags.text = book.tags


        Glide.with(view).load(book.cover)
            .placeholder(R.drawable.ic_launcher_background)
            .into(view.imageView_book)

    }



}
