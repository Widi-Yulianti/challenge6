package com.example.moviedb.movies

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.moviedb.movies.favorite.FavoriteEntity
import com.example.moviedb.room.FavoriteDB
import com.example.moviedb.room.FavoriteDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoriteViewModel(application: Application) : AndroidViewModel(application) {

    private var favoriteDao: FavoriteDao?
    private var favoriteDB: FavoriteDB?

    init {
        favoriteDB = FavoriteDB.getDatabase(application)
        favoriteDao = favoriteDB?.favoriteDao()
    }

    fun addToFavorite(id: Int,
                      originalTitle: String,
                      posterPath: String,
                       ) {
        CoroutineScope(Dispatchers.IO).launch {
            var movie = FavoriteEntity(id, originalTitle, posterPath)
            favoriteDao?.insertFavorite(movie)
        }
    }

    suspend fun checkUser(id: Int) = favoriteDao?.checkMovie(id)

    fun removeFromFavorite(id: Int,
                           originalTitle: String,
                           posterPath: String,
                           ) {
        CoroutineScope(Dispatchers.IO).launch {
            var movie = FavoriteEntity(id, originalTitle, posterPath)
            favoriteDao?.deleteFavorite(movie)
        }
    }

    fun getFavoriteMovie(): LiveData<List<FavoriteEntity>>? {
        return favoriteDao?.getDataFavorite()
    }
}