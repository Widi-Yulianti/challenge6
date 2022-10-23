package com.example.moviedb.movies.favorite

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class FavoriteEntity(
    @field:ColumnInfo(name = "id")
    @field:PrimaryKey
    var id: Int,

    @field:ColumnInfo(name = "original_title")
    val originalTitle: String,

    @field:ColumnInfo(name = "poster_path")
    val posterPath: String,

): Parcelable
