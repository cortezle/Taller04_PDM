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
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.biblioteca.BookListAdapter

import com.example.biblioteca.R
import com.example.biblioteca.database.entities.Author
import com.example.biblioteca.database.entities.Book
import com.example.biblioteca.database.viewmodels.BookViewModel
import com.example.biblioteca.utils.AppConstants
import kotlinx.android.synthetic.main.content_main.view.*
import kotlinx.android.synthetic.main.fragment_list.view.*


class ListFragment : Fragment() {

    lateinit var bookViewModel: BookViewModel
    lateinit var bookadapter: BookListAdapter
    var flag : Boolean = true
    var click:OnFragmentInteractionListener? =  null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list, container, false)
        bookViewModel = ViewModelProviders.of(this).get(BookViewModel::class.java)

        if(savedInstanceState != null) flag = savedInstanceState.getBoolean(AppConstants.FLAG_KEY)

        initRecyclerView(resources.configuration.orientation ,view)
        return view
    }

    fun initRecyclerView(orientation : Int, container: View){
        val linearLayoutManager = LinearLayoutManager(this.context)
        if(orientation == Configuration.ORIENTATION_PORTRAIT) bookadapter = BookListAdapter({ book : Book -> click?.portraitItemClick(book)})
        if(orientation == Configuration.ORIENTATION_LANDSCAPE) bookadapter = BookListAdapter( {book : Book -> click?.landscapeItemClick(book)})
        container.recyclerviewList.adapter = bookadapter
        if(flag){
            bookViewModel.allBooks.observe(this, Observer { boooks ->
                boooks?.let{bookadapter.setWords(it)}
            })
        } else{
            bookViewModel.favoriteBooks.observe(this, Observer { boooks ->
                boooks?.let{bookadapter.setWords(it)}
            })
        }

        container.recyclerviewList.apply {
            setHasFixedSize(true)
            layoutManager = linearLayoutManager
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(AppConstants.FLAG_KEY, flag)
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
        fun newInstance(flag : Boolean): ListFragment{
            val newFragment = ListFragment()
            newFragment.flag = flag
            return newFragment
        }
    }
}
