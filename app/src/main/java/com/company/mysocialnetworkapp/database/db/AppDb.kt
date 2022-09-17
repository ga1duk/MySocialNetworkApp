package com.company.mysocialnetworkapp.database.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.company.mysocialnetworkapp.database.dao.PostDao
import com.company.mysocialnetworkapp.database.entity.PostEntity
import ru.netology.nmedia.database.typeconverter.AttachmentConverter

@Database(entities = [PostEntity::class], version = 1, exportSchema = false)
@TypeConverters(AttachmentConverter::class)
abstract class AppDb : RoomDatabase() {
    abstract fun postDao(): PostDao
}