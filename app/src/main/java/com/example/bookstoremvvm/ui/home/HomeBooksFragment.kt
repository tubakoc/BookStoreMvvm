package com.example.bookstoremvvm.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.bookstoremvvm.R
import com.example.bookstoremvvm.common.viewBinding
import com.example.bookstoremvvm.databinding.FragmentHomeBooksBinding
import com.example.bookstoremvvm.ui.home.adapter.BookListener
import com.example.bookstoremvvm.ui.home.adapter.BooksAdapter
import com.example.bookstoremvvm.ui.viewmodel.HomeBooksViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeBooksFragment : Fragment(R.layout.fragment_home_books), BookListener {

    private val binding by viewBinding (FragmentHomeBooksBinding::bind)

    private val viewModel by viewModels<HomeBooksViewModel> ()

    private val booksAdapter by lazy{ BooksAdapter(this) }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvBooks.adapter = booksAdapter

        viewModel.getBooks()

        observeData()

    }

    private fun observeData()
    {
        viewModel.loadingLiveData.observe(viewLifecycleOwner)
        {
            binding.loading.isVisible = it
        }
        //dinleme başlatıldı
        viewModel.booksLiveData.observe(viewLifecycleOwner){list ->
            if (list != null)
            {
                booksAdapter.submitList(list)
            } else{
                Snackbar.make(requireView(),"Empty list!!",1000).show()

            }
        }
        viewModel.errorMessageLiveData.observe(viewLifecycleOwner){
            Snackbar.make(requireView(),it,1000).show()
        }
    }

    override fun onBookClick(id: Int) {
        val action = HomeBooksFragmentDirections.homeToDetail(id)
        findNavController().navigate(action)
    }

}