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
import com.example.movieapihomework.adapter.NewAdapter
import com.example.movieapihomework.adapter.UpcomingAdapter
import com.example.movieapihomework.databinding.FragmentNewMovieBinding
import com.example.movieapihomework.databinding.FragmentUpcomingBinding
import com.example.movieapihomework.model.UIState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UpcomingFragment : Fragment() {
    private val binding by lazy{
        FragmentNewMovieBinding.inflate(layoutInflater)
    }
    private val adapterV by lazy{
        UpcomingAdapter()
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

        binding.newRe.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
            adapter = adapterV
        }


        moviesViewModel.upcoming.observe(viewLifecycleOwner) { state->
            when(state){
                is UIState.LOADING ->{
                    Toast.makeText(context, "Loading data", Toast.LENGTH_LONG).show()
                }
                is UIState.SUCCESS2 -> {
                    Toast.makeText(context, "Data Loaded", Toast.LENGTH_LONG).show()
                    adapterV.updateUpcomingMovies(state.newUpcoming)
                }
                is UIState.ERROR ->{
                    Toast.makeText(context, "Data Failed", Toast.LENGTH_LONG).show()
                    Log.e("Network","onFailure: ${state.error.localizedMessage}")
                }
            }
        }


        return binding.root
    }
}
