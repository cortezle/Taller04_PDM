package com.example.biblioteca

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.example.biblioteca.database.viewmodels.BookViewModel
import kotlinx.android.synthetic.main.activity_new_book.*

class NewBookActivity : AppCompatActivity() {

    private lateinit var bookViewModel: BookViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_book)

        bookViewModel = ViewModelProviders.of(this).get(BookViewModel::class.java)

        val buttonSave = findViewById<Button>(R.id.button_save)

        buttonSave.setOnClickListener {

            if(TextUtils.isEmpty(editText_title.text) && TextUtils.isEmpty(editText_author.text)
                && TextUtils.isEmpty(editText_edition.text) && TextUtils.isEmpty(editText_editorial.text)
                && TextUtils.isEmpty(editText_isbn.text) && TextUtils.isEmpty(editText_synopsis.text)
                && TextUtils.isEmpty(editText_tags.text)){
                Toast.makeText(applicationContext, "Please fill all the fields",Toast.LENGTH_SHORT).show()
            } else{

            }

            /*val replyIntent = Intent()
            if( TextUtils.isEmpty(editTitleView.text) && TextUtils.isEmpty(editAuthorView.text)
                    && TextUtils.isEmpty(editEditionView.text) && TextUtils.isEmpty(editEditorialView.text)
                    && TextUtils.isEmpty(editIsbnView.text) && TextUtils.isEmpty(editSynopsisView.text)
                    && TextUtils.isEmpty(editTagView.text)){
                setResult(Activity.RESULT_CANCELED, replyIntent)
            }else{
                replyIntent.putExtra(TEXT_KEY_TITLE, editTitleView.text.toString())
                replyIntent.putExtra(TEXT_KEY_AUTHOR, editAuthorView.text.toString())
                replyIntent.putExtra(TEXT_KEY_EDITION, editEditionView.text.toString())
                replyIntent.putExtra(TEXT_KEY_EDITORIAL, editEditorialView.text.toString())
                replyIntent.putExtra(TEXT_KEY_ISBN, editIsbnView.text.toString())
                replyIntent.putExtra(TEXT_KEY_SYNOPSIS, editSynopsisView.text.toString())
                replyIntent.putExtra(TEXT_KEY_TAG, editTagView.text.toString())
                setResult(Activity.RESULT_OK,replyIntent)
            }*/
            finish()
        }

    }
    companion object{
        const val TEXT_KEY_COVER = "cover"
        const val TEXT_KEY_TITLE = "title"
        const val TEXT_KEY_AUTHOR = "author"
        const val TEXT_KEY_EDITION = "edition"
        const val TEXT_KEY_EDITORIAL = "editorial"
        const val TEXT_KEY_ISBN = "isbn"
        const val TEXT_KEY_SYNOPSIS = "synopsis"
        const val TEXT_KEY_TAG = "tag"
    }
}
