package com.ranosan.challenge.boof.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ranosan.challenge.boof.core.data.source.local.entity.FavoriteEntity

@Database(entities = [FavoriteEntity::class], version = 1, exportSchema = false)
abstract class BoofDatabase : RoomDatabase() {
    abstract fun favDao(): FavoriteDao
}