package com.company.mysocialnetworkapp.api

import com.company.mysocialnetworkapp.dto.Post
import com.company.mysocialnetworkapp.dto.User
import retrofit2.Response
import retrofit2.http.*

interface PostsApiService {
    @GET("posts")
    suspend fun getPosts(): Response<List<Post>>

    @POST("posts/{post_id}/likes")
    suspend fun likePostById(@Path("post_id") id: Long): Response<Post>

    @DELETE("posts/{post_id}/likes")
    suspend fun dislikePostById(@Path("post_id") id: Long): Response<Post>

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