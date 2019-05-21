package com.example.biblioteca

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.biblioteca.utils.AppConstants
import kotlinx.android.synthetic.main.activity_coin_view.*
import kotlinx.android.synthetic.main.fragment_main_content.*
import kotlinx.android.synthetic.main.fragment_main_content.iv_book2
import kotlinx.android.synthetic.main.fragment_main_content.tv_autores
import kotlinx.android.synthetic.main.fragment_main_content.tv_edicion
import kotlinx.android.synthetic.main.fragment_main_content.tv_titulo

class BookViewer : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin_view)


        val mIntent = intent

        if(mIntent != null) {

            Glide.with(this)
                .load(mIntent.getStringExtra(AppConstants.TEXT_KEY_COVER))
                .into(iv_book2)

            tv_titulo.text = mIntent.getStringExtra(AppConstants.TEXT_KEY_TITLE)
            tv_autores.text = mIntent.getStringExtra(AppConstants.TEXT_KEY_AUTHOR)
            tv_edicion.text = mIntent.getStringExtra(AppConstants.TEXT_KEY_EDITION)
            tv_edicion.text = mIntent.getStringExtra(AppConstants.TEXT_KEY_EDITION)
            tv_isbn.text = mIntent.getStringExtra(AppConstants.TEXT_KEY_ISBN)
            tv_resumen.text = mIntent.getStringExtra(AppConstants.TEXT_KEY_SYNOPSIS)
            tv_tax.text = mIntent.getStringExtra(AppConstants.TEXT_KEY_TAG)
        }

        }
}