package com.company.mysocialnetworkapp.auth

import com.company.mysocialnetworkapp.repository.TokenRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppAuth @Inject constructor(
    private val tokenRepository: TokenRepository
) {

    private val _authStateFlow: MutableStateFlow<AuthState>

    init {
        val id = tokenRepository.userId
        val token = tokenRepository.token

        if (id == 0L || token == null) {
            _authStateFlow = MutableStateFlow(AuthState())
            tokenRepository.clearPrefs()
        } else {
            _authStateFlow = MutableStateFlow(AuthState(id, token))
        }
    }

    val authStateFlow: StateFlow<AuthState> = _authStateFlow.asStateFlow()

    @Synchronized
    fun setAuth(id: Long, token: String) {
        _authStateFlow.value = AuthState(id, token)
        tokenRepository.userId = id
        tokenRepository.token = token
    }

    @Synchronized
    fun removeAuth() {
        _authStateFlow.value = AuthState()
        tokenRepository.clearPrefs()
    }
}

data class AuthState(val id: Long = 0, val token: String? = null)