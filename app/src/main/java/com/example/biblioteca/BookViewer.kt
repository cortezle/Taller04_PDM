package com.example.biblioteca

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.biblioteca.utils.AppConstants
import kotlinx.android.synthetic.main.fragment_main_content.*


class BookViewer : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_view)


        val mIntent = intent

        if(mIntent != null) {

            Glide.with(this)
                .load(mIntent.getStringExtra(AppConstants.TEXT_KEY_COVER))
                .into(imageView_book)

            textView_title.text = mIntent.getStringExtra(AppConstants.TEXT_KEY_TITLE)
            textView_Author.text = mIntent.getStringExtra(AppConstants.TEXT_KEY_AUTHOR)
            textView_Edition.text = mIntent.getStringExtra(AppConstants.TEXT_KEY_EDITION)
            textView_Editorial.text = mIntent.getStringExtra(AppConstants.TEXT_KEY_EDITORIAL)
            textView_Isbn.text = mIntent.getStringExtra(AppConstants.TEXT_KEY_ISBN)
            textView_Synopsis.text = mIntent.getStringExtra(AppConstants.TEXT_KEY_SYNOPSIS)
            textView_Tags.text = mIntent.getStringExtra(AppConstants.TEXT_KEY_TAG)
        }

        }
}