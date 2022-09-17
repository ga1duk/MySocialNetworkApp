package com.company.mysocialnetworkapp.repository

import com.company.mysocialnetworkapp.dto.Post

interface PostRepository {
suspend fun getAll(): List<Post>
}