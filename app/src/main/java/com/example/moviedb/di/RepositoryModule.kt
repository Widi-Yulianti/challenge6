package com.example.moviedb.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.example.moviedb.repository.MovieMapper
import com.example.moviedb.repository.MovieMapperImpl
import com.example.moviedb.repository.MovieRepository
import com.example.moviedb.repository.MovieRepositoryImpl
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
interface RepositoryModule {

    @Binds
    @Singleton
    fun provideMovieMapper(movieMapperImpl: MovieMapperImpl): MovieMapper

    @Binds
    @Singleton
    fun provideBookRepository(movieRepositoryImpl: MovieRepositoryImpl): MovieRepository

}