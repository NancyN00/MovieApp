package com.example.movieapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.models.TvShowsItem
import com.example.movieapp.repository.TvShowRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TvShowViewModel
     @Inject
     constructor(private val repository: TvShowRepository):ViewModel() {

    private val _response = MutableLiveData<List<TvShowsItem>>()
    val responseTvShow: LiveData<List<TvShowsItem>>
        get() = _response

    init {
        getAllTvShows()
    }

    private fun getAllTvShows() = viewModelScope.launch {
        repository.getTvShows().let { response ->
            if (response.isSuccessful) {
                _response.postValue(response.body())
            } else {
                Log.d("tag", "getAllTvShows Error: ${response.code()} ")
            }
        }
    }
}



