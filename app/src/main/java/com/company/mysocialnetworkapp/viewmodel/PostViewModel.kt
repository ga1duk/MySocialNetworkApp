package com.company.mysocialnetworkapp.viewmodel

import androidx.lifecycle.*
import com.company.mysocialnetworkapp.auth.AppAuth
import com.company.mysocialnetworkapp.dto.Post
import com.company.mysocialnetworkapp.repository.PostRepository
import com.company.mysocialnetworkapp.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
@ExperimentalCoroutinesApi
class PostViewModel @Inject constructor(
    private val repository: PostRepository,
    private val appAuth: AppAuth
) : ViewModel() {

    private val _data = MutableLiveData<List<Post>>()
    val data: LiveData<List<Post>>
        get() = _data

    private val _isUserAuthorized = SingleLiveEvent<Boolean>()
    val isUserAuthorized: LiveData<Boolean>
        get() = _isUserAuthorized

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

    fun likePostById(id: Long) = viewModelScope.launch {
        try {
            repository.likePostById(id)
            getPosts()
        } catch (e: Exception) {
            TODO()
        }
    }

    fun dislikePostById(id: Long) = viewModelScope.launch {
        try {
            repository.dislikePostById(id)
            getPosts()
        } catch (e: Exception) {
            TODO()
        }
    }

    fun checkForUsersAuthentication(): Boolean {
        _isUserAuthorized.value = !(appAuth.authStateFlow.value.id == 0L
                || appAuth.authStateFlow.value.token == null)
        return _isUserAuthorized.value ?: false
    }
}
