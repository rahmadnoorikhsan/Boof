package com.ranosan.challenge.boof.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ranosan.challenge.boof.core.data.source.local.entity.FavoriteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(favoriteEntity: FavoriteEntity)

    @Insert
    suspend fun updateFavorite(favoriteEntity: FavoriteEntity)

    @Query("DELETE FROM fav_entity WHERE id = :id")
    suspend fun delete(id: Int)

    @Query("SELECT * FROM fav_entity")
    fun getAllFavorite(): Flow<List<FavoriteEntity>>

    @Query("SELECT EXISTS (SELECT * FROM fav_entity WHERE id = :id)")
    fun isFavorite(id: Int): Flow<Boolean>
}