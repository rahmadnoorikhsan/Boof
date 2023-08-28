package com.ranosan.challenge.boof.core.domain.repository

import com.ranosan.challenge.boof.core.data.source.local.entity.FavoriteEntity
import com.ranosan.challenge.boof.core.domain.model.Favorite
import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {
    fun isFavorite(id: Int): Flow<Boolean>

    fun getAllFavorite(): Flow<List<Favorite>>

    suspend fun insertFavorite(fav: FavoriteEntity)

    suspend fun updateFavorite(fav: FavoriteEntity)

    suspend fun deleteFavorite(id: Int)
}