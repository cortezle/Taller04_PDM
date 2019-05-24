package com.example.biblioteca

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.biblioteca.database.entities.Book
import com.example.biblioteca.database.viewmodels.BookViewModel
import com.example.biblioteca.fragments.ListFragment
import com.example.biblioteca.fragments.MainContentFragment
import com.example.biblioteca.utils.AppConstants

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_new_book.*

class MainActivity : AppCompatActivity(), ListFragment.OnFragmentInteractionListener {

    private lateinit var bookViewModel: BookViewModel
    private lateinit var listFragment: ListFragment
    private val newBookActivityRequestCode = 1



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        bookViewModel = ViewModelProviders.of(this).get(BookViewModel::class.java)
        listFragment = ListFragment.newInstance(bookViewModel)
        val resource = if(resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT)
            R.id.frameLayoutMain
        else {
            R.id.land_main_fragment
        }
        changeFragment(resource, listFragment)
        fab.setOnClickListener { view ->
            val intent = Intent(this@MainActivity, NewBookActivity::class.java)
            startActivityForResult(intent, newBookActivityRequestCode)
        }

        /*button_save.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivityForResult(intent, newBookActivityRequestCode)
        }*/


    }

    private fun changeFragment(id: Int, frag: Fragment){ supportFragmentManager.beginTransaction().replace(id, frag).commit() }

    override fun portraitItemClick(book: Book) {
        var mIntent = Intent(this, BookViewer::class.java)
        var bundle = Bundle()
        bundle.putParcelable(AppConstants.TEXT_KEY_BOOK, book)
        mIntent.putExtras(bundle)
        startActivity(mIntent)
    }

    override fun landscapeItemClick(book: Book) {
        var contentFragment = MainContentFragment.newInstance(book)
        changeFragment(R.id.land_main_cont_fragment, contentFragment)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
