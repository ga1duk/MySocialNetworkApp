package com.company.mysocialnetworkapp.repository

import com.company.mysocialnetworkapp.api.PostsApiService
import com.company.mysocialnetworkapp.dto.User
import ru.netology.nmedia.error.*
import java.io.IOException
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val apiService: PostsApiService
) : UserRepository {

    override suspend fun authenticateUser(login: String, password: String): User {
        try {
            val response = apiService.authenticateUser(login, password)
            if (!response.isSuccessful) {
                throw ApiError(response.code(), response.message())
            }
            return response.body() ?: throw ApiError(response.code(), response.message())
        } catch (e: IOException) {
            throw NetworkError
        } catch (e: ApiError) {
            throw when (e.status) {
                400, 404 -> LoginOrPassError
                else -> UnknownError
            }
        } catch (e: Exception) {
            throw UnknownError
        }
    }

    override suspend fun registerUser(name: String, login: String, password: String): User {
        try {
            val response = apiService.registerUser(name, login, password)
            if (!response.isSuccessful) {
                throw ApiError(response.code(), response.message())
            }
            return response.body() ?: throw ApiError(response.code(), response.message())
        } catch (e: ApiError) {
            throw when (e.status) {
                400, 403 -> LoginOrPassError
                500 -> InternalServerError
                else -> UnknownError
            }
        } catch (e: IOException) {
            throw NetworkError
        } catch (e: Exception) {
            throw UnknownError
        }
    }
}
