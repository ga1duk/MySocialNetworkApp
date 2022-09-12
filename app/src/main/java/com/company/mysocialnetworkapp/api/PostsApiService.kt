package com.company.mysocialnetworkapp.api

import com.company.mysocialnetworkapp.dto.Post
import retrofit2.Response
import retrofit2.http.GET

interface PostsApiService {
    @GET("posts")
    suspend fun getPosts(): Response<List<Post>>
}