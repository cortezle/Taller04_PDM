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
        view.tv_titulo.text = book.title

        view.tv_autores.text = book.authors
        view.tv_edicion.text = book.edition.toString()
        view.tv_editorial.text = book.editorial
        view.tv_isbn.text = book.isbn
        view.tv_resumen.text = book.synopsis
        view.tv_tax.text = book.tags


        Glide.with(view).load(book.cover)
            .placeholder(R.drawable.ic_launcher_background)
            .into(view.iv_book2)

    }



}
