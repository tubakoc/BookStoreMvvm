package com.example.bookstoremvvm

import android.app.Application
import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.bookstoremvvm.common.Constants.BASE_URL
import com.example.bookstoremvvm.data.source.remote.BookService
import dagger.hilt.android.HiltAndroidApp
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@HiltAndroidApp
class MainApplication : Application()