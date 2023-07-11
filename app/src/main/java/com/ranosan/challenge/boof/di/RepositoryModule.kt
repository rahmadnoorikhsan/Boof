package com.ranosan.challenge.boof.di

import com.ranosan.challenge.boof.data.repository.MovieRepositoryImpl
import com.ranosan.challenge.boof.domain.repository.MovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun provideMovieRepository(movieRepositoryImpl: MovieRepositoryImpl):MovieRepository
}