package com.company.mysocialnetworkapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.company.mysocialnetworkapp.auth.AppAuth
import com.company.mysocialnetworkapp.model.SignInModelState
import com.company.mysocialnetworkapp.repository.UserRepository
import com.company.mysocialnetworkapp.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.netology.nmedia.error.LoginOrPassError
import ru.netology.nmedia.error.NetworkError
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val appAuth: AppAuth
) : ViewModel() {

    private val _dataState = SingleLiveEvent<SignInModelState>()
    val dataState: LiveData<SignInModelState>
        get() = _dataState

    fun authenticateUser(login: String, password: String) = viewModelScope.launch {
        try {
            if (login != "" && password != "") {
                val user = userRepository.authenticateUser(login, password)
                appAuth.setAuth(user.id, user.token)
                _dataState.value = SignInModelState()
            } else {
                _dataState.value = SignInModelState(emptyFieldsError = true)
            }
        } catch (e: LoginOrPassError) {
            _dataState.value = SignInModelState(loginOrPassError = true)
        } catch (e: NetworkError) {
            _dataState.value = SignInModelState(networkError = true)
        } catch (e: Exception) {
            _dataState.value = SignInModelState(unknownError = true)
        }
    }
}