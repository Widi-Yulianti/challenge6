package com.example.moviedb.repository

import com.example.moviedb.data.Movie
import com.example.moviedb.data.remote.MovieDtoResponse
import com.example.moviedb.data.remote.MovieService
import com.example.moviedb.di.MoviesNetworkService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.io.IOException
import javax.inject.Inject


interface MovieRepository {
    suspend fun getTopRatedTvShows(page: Int): Flow<Resource<List<Movie>>>
}

class MovieRepositoryImpl constructor(
    private val movieService: MovieService,
    private val movieMapper: MovieMapper,
    private val coroutineDispatcher: CoroutineDispatcher
) : MovieRepository {

    @Inject
    constructor(
        //@FakeMoviesNetworkService movieService: MovieService,
        @MoviesNetworkService movieService: MovieService,
        movieMapper: MovieMapper,
    ) : this(movieService, movieMapper, Dispatchers.IO)


    override suspend fun getTopRatedTvShows(page: Int): Flow<Resource<List<Movie>>> {
        return flow<Resource<List<Movie>>> {
            emit(Resource.Loading())
            try {
                val topRatedTVShows: MovieDtoResponse = movieService.getTopRatedTVShows(page)
                topRatedTVShows.movies.map {
                    movieMapper.mapToMovie(it)
                }.let {
                    emit(Resource.Success(it))
                }
            } catch (ex: IOException) {
                emit(Resource.Error("Error :" + ex.message))
            }

        }.flowOn(coroutineDispatcher)
    }

}

