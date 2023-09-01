package com.example.bookstoremvvm.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.example.bookstoremvvm.MainApplication
import com.example.bookstoremvvm.R
import com.example.bookstoremvvm.common.viewBinding
import com.example.bookstoremvvm.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

   private val binding by viewBinding (ActivityMainBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        window.statusBarColor  = ContextCompat.getColor(this, R.color.bck)
    }
}