package com.company.mysocialnetworkapp.repository

import com.company.mysocialnetworkapp.api.PostsApiService
import com.company.mysocialnetworkapp.database.dao.PostDao
import com.company.mysocialnetworkapp.database.entity.toEntity
import com.company.mysocialnetworkapp.dto.Post
import ru.netology.nmedia.error.ApiError
import ru.netology.nmedia.error.NetworkError
import ru.netology.nmedia.error.UnknownError
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PostRepositoryImpl @Inject constructor(
    private val apiService: PostsApiService,
    private val dao: PostDao
) :
    PostRepository {

    override suspend fun getAll(): List<Post> {
        try {
            val response = apiService.getPosts()
            if (!response.isSuccessful) {
                throw ApiError(response.code(), response.message())
            }
            val body = response.body() ?: throw ApiError(response.code(), response.message())
            dao.insert(body.toEntity())
            return body
        } catch (e: IOException) {
            throw NetworkError
        } catch (e: Exception) {
            throw UnknownError
        }
    }

    override suspend fun likePostById(id: Long) {
        try {
            val response = apiService.likePostById(id)
            if (!response.isSuccessful) {
                throw ApiError(response.code(), response.message())
            }
            //            dao.insert(body.toEntity())
        } catch (e: IOException) {
            throw NetworkError
        } catch (e: Exception) {
            throw UnknownError
        }
    }

    override suspend fun dislikePostById(id: Long) {
        try {
            val response = apiService.dislikePostById(id)
            if (!response.isSuccessful) {
                throw ApiError(response.code(), response.message())
            }
            //            dao.insert(body.toEntity())
        } catch (e: IOException) {
            throw NetworkError
        } catch (e: Exception) {
            throw UnknownError
        }
    }
}