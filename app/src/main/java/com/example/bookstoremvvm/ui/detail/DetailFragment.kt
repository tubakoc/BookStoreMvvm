package com.example.bookstoremvvm.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.bookstoremvvm.R
import com.example.bookstoremvvm.common.loadImage
import com.example.bookstoremvvm.common.viewBinding
import com.example.bookstoremvvm.databinding.FragmentDetailBinding
import com.example.bookstoremvvm.ui.viewmodel.DetailViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.fragment_detail) {
    private val binding by viewBinding (FragmentDetailBinding::bind)

    private val viewModel by viewModels<DetailViewModel> ()

    private val args by navArgs<DetailFragmentArgs>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getBookDetail(args.id)

        observeData()
    }
    private fun observeData () = with(binding){

        viewModel.loadingLiveData.observe(viewLifecycleOwner)
        {
            binding.loading.isVisible = it
        }

        viewModel.bookDetailLiveData.observe(viewLifecycleOwner){book->
            if (book != null)
            {
                    tvTitles.text = book.name
                    tvPrices.text= "${book.price} ₺ "
                    tvDescription.text = "Author: ${book.author}"
                    tvPublisher.text = "Publisher: ${book.publisher}"
                    ivBooks.loadImage(book.imageUrl)
            } else{
                Snackbar.make(requireView(),"Empty list!!",1000).show()
            }

        }
        viewModel.errorMessageLiveData.observe(viewLifecycleOwner){
            Snackbar.make(requireView(),it,1000).show()

        }
    }



}