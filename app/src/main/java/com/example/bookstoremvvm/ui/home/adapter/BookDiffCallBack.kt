package com.example.bookstoremvvm.ui.home.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.bookstoremvvm.data.model.Book

class BookDiffCallBack : DiffUtil.ItemCallback<Book> () {
    override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean {
        return oldItem == newItem
    }
}