package com.ranosan.challenge.boof.core.di

import android.content.Context
import androidx.room.Room
import com.ranosan.challenge.boof.core.data.source.local.room.BoofDatabase
import com.ranosan.challenge.boof.core.data.source.local.room.FavoriteDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun boofDatabase(@ApplicationContext context: Context): BoofDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            BoofDatabase::class.java,
            "boof_database.db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideFavoriteDao(boofDatabase: BoofDatabase): FavoriteDao {
        return boofDatabase.favDao()
    }
}