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
import com.example.biblioteca.database.entities.Author
import com.example.biblioteca.database.entities.Book
import com.example.biblioteca.database.viewmodels.BookViewModel
import kotlinx.android.synthetic.main.activity_new_book.*

class NewBookActivity : AppCompatActivity() {

    private lateinit var bookViewModel: BookViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_book)

        bookViewModel = ViewModelProviders.of(this).get(BookViewModel::class.java)

        val buttonSave = findViewById<Button>(R.id.button_save)

        buttonSave.setOnClickListener {click()}
    }

    fun click(){
        if(TextUtils.isEmpty(editText_title.text) && TextUtils.isEmpty(editText_author.text)
            && TextUtils.isEmpty(editText_edition.text) && TextUtils.isEmpty(editText_editorial.text)
            && TextUtils.isEmpty(editText_isbn.text) && TextUtils.isEmpty(editText_synopsis.text)
            && TextUtils.isEmpty(editText_tags.text)){
            Toast.makeText(applicationContext, "Please fill all the fields",Toast.LENGTH_SHORT).show()
        } else{
            var title = editText_title.text.toString()
            var edicion = editText_edition.text.toString().toInt()
            var authors = subString(editText_author.text.toString())
            var edits = subString(editText_editorial.text.toString())
            var id = editText_isbn.text.toString()
            var synopsis = editText_synopsis.text.toString()
            var tags = subString(editText_tags.text.toString())

            var book = Book(title, "cover.jpg", edicion, synopsis, id, 0)
            bookViewModel.insertBook(book)
            bookViewModel.insertListAuthor(authors, book)
            bookViewModel.insertListEditorial(edits, book)
            bookViewModel.insertListTag(tags, book)
        }
        finish()
    }

    fun subString(campo : String) : ArrayList<String>{
        var array = ArrayList<String>()
        var index0 = 0
        var indexf = 0
        for(i in 0..campo.length-1){
            if(campo[i] == ','){
                array.add(campo.substring(index0, indexf))
                index0 = i+1
            }
            else if(campo[i] != ' ' ){
                indexf = i
            }
            if(campo[index0] == ' '){
                index0 += 1
            }
        }
        array.add(campo.substring(index0, indexf))
        return array
    }
}
