package com.example.biblioteca

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.biblioteca.database.entities.Book
import com.example.biblioteca.utils.AppConstants
import kotlinx.android.synthetic.main.activity_book_view.*
import kotlinx.android.synthetic.main.fragment_main_content.*


class BookViewer : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_view)

        val receiver : Book = intent?.extras?.getParcelable(AppConstants.TEXT_KEY_BOOK) ?: Book(
            "N/A",
            "N/A",
            1,
            "N/A",
            "N/A",
            0)
        init(receiver)
    }

    fun init(book: Book){
        Glide.with(this)
            .load(book.cover)
            .placeholder(R.drawable.ic_launcher_background)
            .into(imageView_book_viewer)
        textView_title_viewer.text = book.title
        textView_Edition_viewer.text = book.edition.toString()
        textView_Isbn_viewer.text = book.idBook
        textView_Synopsis_viewer.text = book.synopsis

    }
}