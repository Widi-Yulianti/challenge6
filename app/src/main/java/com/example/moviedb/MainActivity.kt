package com.example.moviedb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import com.example.moviedb.movies.home.HomeFragment
import com.example.moviedb.movies.listing.MoviesFragment
import com.example.moviedb.movies.movie.MovieFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, HomeFragment())
                .commit()
        }


    }

    fun navigateToTopRatedTvShows() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, MoviesFragment())
            .addToBackStack(MoviesFragment.getFragmentTag())
            .commitAllowingStateLoss()
    }

    fun navigateMovieView() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, MovieFragment())
            .addToBackStack(MovieFragment.getFragmentTag())
            .commitAllowingStateLoss()
    }

}