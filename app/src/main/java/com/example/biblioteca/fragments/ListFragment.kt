package com.example.biblioteca.fragments

import android.content.Context
import android.content.res.Configuration
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.biblioteca.BookListAdapter

import com.example.biblioteca.R
import com.example.biblioteca.database.entities.Book
import kotlinx.android.synthetic.main.content_main.view.*
import kotlinx.android.synthetic.main.fragment_list.view.*


class ListFragment : Fragment() {

    lateinit var books: LiveData<List<Book>>
    lateinit var bookadapter: BookListAdapter
    var click:OnFragmentInteractionListener? =  null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list, container, false)
        initRecyclerView(resources.configuration.orientation ,view)
        return view
    }

    fun initRecyclerView(orientation : Int, container: View){
        val linearLayoutManager = LinearLayoutManager(this.context)
        if(orientation == Configuration.ORIENTATION_PORTRAIT) bookadapter = BookListAdapter(books, { book : Book -> click?.portraitItemClick(book)})
        if(orientation == Configuration.ORIENTATION_LANDSCAPE) bookadapter = BookListAdapter(books, {book : Book -> click?.landscapeItemClick(book)})
        container.recyclerview.adapter = bookadapter
        container.recyclerview.apply {
            setHasFixedSize(true)
            layoutManager = linearLayoutManager
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            click = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        click = null
    }

    interface OnFragmentInteractionListener {
        fun portraitItemClick(book: Book)

        fun landscapeItemClick(book:Book)
    }

    companion object {
        fun newInstance(book: LiveData<List<Book>>): ListFragment{
            val newFragment = ListFragment()
            newFragment.books = book
            return newFragment

        }
    }
}
