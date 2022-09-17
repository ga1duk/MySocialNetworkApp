package com.company.mysocialnetworkapp.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.company.mysocialnetworkapp.database.entity.PostEntity

@Dao
interface PostDao {
    @Query("SELECT * FROM PostEntity")
    fun getPosts(): List<PostEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(posts: List<PostEntity>)
}