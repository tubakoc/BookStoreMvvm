package com.example.bookstoremvvm.data.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.bookstoremvvm.data.model.Book
import com.example.bookstoremvvm.data.model.GetBookDetailResponce
import com.example.bookstoremvvm.data.model.GetBooksResponce
import com.example.bookstoremvvm.data.source.remote.BookService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BooksRepository (private val bookService: BookService){

    val booksLiveData = MutableLiveData<List<Book>?>()
    val bookDetailLiveData = MutableLiveData<Book?>()
    val errorMessageLiveData = MutableLiveData<String>()
    val loadingLiveData = MutableLiveData<Boolean>()

    fun getBooks()
    {
        loadingLiveData.value = true
        bookService.getProducts().enqueue(object : Callback<GetBooksResponce> {
            override fun onResponse(
                call: Call<GetBooksResponce>,
                response: Response<GetBooksResponce>
            ) {

                val result = response.body()?.books


                if (result.isNullOrEmpty().not()) {
                    booksLiveData.value = result
                } else
                {
                    booksLiveData.value = null
                }
                loadingLiveData.value = false


            }

            override fun onFailure(call: Call<GetBooksResponce>, t: Throwable) {
                errorMessageLiveData.value = t.message.orEmpty()
                loadingLiveData.value = false
                Log.e("GetProducts", t.message.orEmpty())
            }

        })
    }

     fun getBookDetail(id:Int) {
         loadingLiveData.value = true
         bookService.getBookDetail(id).enqueue(object : Callback<GetBookDetailResponce> {
             override fun onResponse(
                 call: Call<GetBookDetailResponce>,
                 response: Response<GetBookDetailResponce>
             ) {
                 bookDetailLiveData.value = response.body()?.book
                 loadingLiveData.value = false
             }

             override fun onFailure(call: Call<GetBookDetailResponce>, t: Throwable) {
                 errorMessageLiveData.value = t.message.orEmpty()
                 loadingLiveData.value = false
                 Log.e("GetProducts", t.message.orEmpty())
             }

         })
     }
}