package com.example.bookstoremvvm.data.model

data class GetBooksResponce(

    val books: List<Book>?,
    val message: String?,
    val success: Int?
)
