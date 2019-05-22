package com.example.biblioteca

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ExpandableListView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.biblioteca.database.entities.Book
import kotlinx.android.synthetic.main.book_model.view.*

class BookListAdapter (val clickListener: (Book) -> Unit): RecyclerView.Adapter<BookListAdapter.ViewHolder>(){

    private var books = emptyList<Book>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookListAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.book_model,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return books.size
    }

    override fun onBindViewHolder(holder: BookListAdapter.ViewHolder, position: Int) {
        val current = books[position]
        holder.bind(current,clickListener)
    }

    internal fun setWords(books : List<Book>){
        this.books = books
        notifyDataSetChanged()
    }

    class ViewHolder(itemView : View): RecyclerView.ViewHolder(itemView){
        fun bind(item: Book,clickListener: (Book) -> Unit) = with(itemView){
            Glide.with(itemView.context)
                .load(item.cover)
                .into(itemView.image_book)

            name_book.text = item.title
            this.setOnClickListener{clickListener(item)}
        }
    }
}