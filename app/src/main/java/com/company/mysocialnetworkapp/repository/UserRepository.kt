package com.company.mysocialnetworkapp.repository

import com.company.mysocialnetworkapp.dto.User

interface UserRepository {
    suspend fun authenticateUser(login: String, password: String): User
    suspend fun registerUser(name: String, login: String, password: String): User
}
