package com.example.movieapihomework.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapihomework.adapter.Adapter
import com.example.movieapihomework.api.PopularREImpl
import com.example.movieapihomework.api.PopularRe
import com.example.movieapihomework.databinding.FragmentPopularBinding
import com.example.movieapihomework.model.UIState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PopularFragment() : Fragment() {

    private val binding by lazy{
        FragmentPopularBinding.inflate(layoutInflater)
    }
    private val adapterV by lazy{
        Adapter()
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

        binding.popRe.apply {
            layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
            adapter = adapterV
        }


        moviesViewModel.popular.observe(viewLifecycleOwner) { state->
            when(state){
                is UIState.LOADING ->{
                    Toast.makeText(context, "Loading data",Toast.LENGTH_LONG).show()
                }
                is UIState.SUCCESS -> {
                    Toast.makeText(context, "Data Loaded",Toast.LENGTH_LONG).show()
                    Log.d("Size",state.popular.size.toString())
                    adapterV.updatePopMovies(state.popular)
                }
                is UIState.ERROR ->{
                    Toast.makeText(context, "Data Failed",Toast.LENGTH_LONG).show()
                    Log.e("Network","onFailure: ${state.error.localizedMessage}")
                }
            }
        }


        return binding.root
    }
}