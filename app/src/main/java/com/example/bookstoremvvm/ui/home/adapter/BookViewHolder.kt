package com.example.bookstoremvvm.ui.home.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.bookstoremvvm.common.loadImage
import com.example.bookstoremvvm.data.model.Book
import com.example.bookstoremvvm.databinding.ItemBooksBinding


class BookViewHolder (private val binding : ItemBooksBinding,
                      private val bookListener: BookListener,
) : RecyclerView.ViewHolder(binding.root)
{
    fun bind(book : Book) = with(binding)
    {
        tvTitles.text = book.name
        tvPrices.text= "${book.price} ₺ "

        ivBook.loadImage(book.imageUrl)

        if(book.isBestSeller == true) sellerImageview.visibility = View.VISIBLE
        else sellerImageview.visibility = View.GONE

        root.setOnClickListener {
            bookListener.onBookClick(book.id ?: 1)
        }

    }

}

interface BookListener{
    fun onBookClick(id:Int)
}