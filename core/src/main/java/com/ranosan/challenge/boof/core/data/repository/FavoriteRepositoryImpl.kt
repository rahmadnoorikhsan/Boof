package com.ranosan.challenge.boof.core.data.repository

import com.ranosan.challenge.boof.core.data.source.local.entity.FavoriteEntity
import com.ranosan.challenge.boof.core.data.source.local.room.FavoriteDao
import com.ranosan.challenge.boof.core.domain.repository.FavoriteRepository
import com.ranosan.challenge.boof.core.util.toDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FavoriteRepositoryImpl @Inject constructor(
    private val favoriteDao: FavoriteDao
) : FavoriteRepository {
    override fun isFavorite(id: Int) = flow {
        try {
            val result = favoriteDao.isFavorite(id)
            emitAll(result)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }.flowOn(Dispatchers.IO)

    override fun getAllFavorite() = flow {
        try {
            val result = favoriteDao.getAllFavorite().map {
                it.toDomain()
            }
            emitAll(result)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun insertFavorite(fav: FavoriteEntity) = favoriteDao.insert(fav)

    override suspend fun updateFavorite(fav: FavoriteEntity) = favoriteDao.updateFavorite(fav)

    override suspend fun deleteFavorite(id: Int) = favoriteDao.delete(id)
}