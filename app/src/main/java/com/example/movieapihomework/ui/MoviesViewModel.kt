package com.example.movieapihomework.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapihomework.api.PopularRe
import com.example.movieapihomework.model.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val popularRe: PopularRe,
    private val ioDispatcher: CoroutineDispatcher
): ViewModel() {

    private val _popular: MutableLiveData<UIState> = MutableLiveData(UIState.LOADING)
    public val popular:  LiveData<UIState> get() = _popular

    private val _new : MutableLiveData<UIState> = MutableLiveData(UIState.LOADING)
    val new : LiveData<UIState> get() = _new

    private val _upcoming : MutableLiveData<UIState> = MutableLiveData(UIState.LOADING)
    val upcoming : LiveData<UIState> get() = _upcoming

    private val _dea : MutableLiveData<UIState> = MutableLiveData(UIState.LOADING)
    val dea : LiveData<UIState> get() = _dea




    init {
        getPopular()
        getNew()
        getUpcoming()
        getDeatils()
    }

    private fun getUpcoming() {
        viewModelScope.launch(ioDispatcher) {
            popularRe.getUpcomingMoives().collect(){
                _upcoming.postValue(it)
            }
        }
    }

    private fun getNew() {
        viewModelScope.launch(ioDispatcher){
            popularRe.getNewMoives().collect(){
                _new.postValue(it)
            }
        }
    }

    private fun getPopular() {
        viewModelScope.launch(ioDispatcher) {
            popularRe.getPopularMoives().collect(){
                _popular.postValue(it)
            }
        }
    }

    private fun getDeatils() {
        viewModelScope.launch(ioDispatcher) {
            popularRe.getMoivesDeat().collect(){
                _dea.postValue(it)
            }
        }
    }
}