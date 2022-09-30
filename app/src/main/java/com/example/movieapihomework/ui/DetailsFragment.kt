package com.example.movieapihomework.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapihomework.adapter.DetailsAdapter
import com.example.movieapihomework.adapter.NewAdapter
import com.example.movieapihomework.databinding.FragmentDetailsBinding
import com.example.movieapihomework.model.MoviesData
import com.example.movieapihomework.model.MoviesDataDetails
import com.example.movieapihomework.model.UIState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment() {
    private val binding by lazy{
        FragmentDetailsBinding.inflate(layoutInflater)
    }
    private val adapterV by lazy{
        DetailsAdapter()
    }
    private val moviesViewModel by lazy{
        ViewModelProvider(requireActivity())[MoviesViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        moviesViewModel.dea.observe(viewLifecycleOwner) { state->
            when(state){
                is UIState.LOADING ->{
                    Toast.makeText(context, "Loading data", Toast.LENGTH_LONG).show()
                }
                is UIState.SUCCESS3 -> {
                    Toast.makeText(context, "Data Loaded", Toast.LENGTH_LONG).show()
                    adapterV.updateDetailsMovies(state.deat)
                }
                is UIState.ERROR ->{
                    Toast.makeText(context, "Data Failed", Toast.LENGTH_LONG).show()
                    Log.e("Network","onFailure: ${state.error.localizedMessage}")
                }
            }
        }

        populateMoivesDeatils(moviesViewModel.deats)
        return binding.root
    }

    private fun populateMoivesDeatils(deats: MoviesData?) {
        binding.Title.text = deats?.title

    }
}
