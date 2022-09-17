package com.company.mysocialnetworkapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.company.mysocialnetworkapp.dto.Post
import com.company.mysocialnetworkapp.repository.PostRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
@ExperimentalCoroutinesApi
class PostViewModel @Inject constructor(private val repository: PostRepository) : ViewModel() {

    val _data = MutableLiveData<List<Post>>()
    val data: LiveData<List<Post>>
        get() = _data

    init {
        getPosts()
    }

    private fun getPosts() {
        viewModelScope.launch {
            try {
                _data.postValue(repository.getAll())
            } catch (e: Exception) {
                TODO()
            }
        }
    }
}
