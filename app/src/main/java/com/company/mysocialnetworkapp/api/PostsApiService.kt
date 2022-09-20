package com.company.mysocialnetworkapp.api

import com.company.mysocialnetworkapp.dto.Post
import com.company.mysocialnetworkapp.dto.User
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface PostsApiService {
    @GET("posts")
    suspend fun getPosts(): Response<List<Post>>

    @FormUrlEncoded
    @POST("users/authentication")
    suspend fun authenticateUser(
        @Field("login") login: String,
        @Field("password") password: String
    ): Response<User>

    @FormUrlEncoded
    @POST("users/registration")
    suspend fun registerUser(
        @Field("name") name: String,
        @Field("login") login: String,
        @Field("password") password: String
    ): Response<User>
}