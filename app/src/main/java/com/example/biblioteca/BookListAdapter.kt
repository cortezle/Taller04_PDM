package com.example.biblioteca

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ExpandableListView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.biblioteca.database.entities.Book
import kotlinx.android.synthetic.main.book_model.view.*

class BookListAdapter (var items:List<Book>, val clickListener: (Book)->Unit): RecyclerView.Adapter<BookListAdapter.ViewHolder>(){





    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookListAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.book_model,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: BookListAdapter.ViewHolder, position: Int) {
        holder.bind(items[position],clickListener)
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