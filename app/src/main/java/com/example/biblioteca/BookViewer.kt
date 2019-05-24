package com.example.biblioteca

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.biblioteca.database.entities.Book
import com.example.biblioteca.database.viewmodels.BookViewModel
import com.example.biblioteca.fragments.MainContentFragment
import com.example.biblioteca.utils.AppConstants
import kotlinx.android.synthetic.main.activity_book_view.*
import kotlinx.android.synthetic.main.fragment_main_content.*


class BookViewer : AppCompatActivity() {

    private lateinit var mainFragment: MainContentFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_view)

        val receiver : Book = intent?.extras?.getParcelable(AppConstants.TEXT_KEY_BOOK)!!
        mainFragment = MainContentFragment.newInstance(receiver)
        changeFragment(R.id.main_cont_fragment, mainFragment)
    }

    private fun changeFragment(id: Int, frag: Fragment){ supportFragmentManager.beginTransaction().replace(id, frag).commit() }

}