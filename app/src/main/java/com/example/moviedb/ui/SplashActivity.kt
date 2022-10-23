package com.example.moviedb.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageView
import androidx.datastore.preferences.core.booleanPreferencesKey
import com.example.moviedb.databinding.ActivitySplashBinding
import com.bumptech.glide.Glide
import com.example.moviedb.R
import kotlinx.coroutines.runBlocking

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Handler(Looper.getMainLooper()).postDelayed({
            verifyUserLogged()
            finish()
        }, 3000)

        showGif(binding.root)
    }

    private fun verifyUserLogged(){
        runBlocking {
            val hasLogin = DataStoreManager.readDataStore(booleanPreferencesKey("HAS_LOGIN")) ?: false
            if(!hasLogin){
                val intent = Intent(this@SplashActivity, LoginActivity::class.java)
                startActivity(intent)
            } else {
                val intent = Intent(this@SplashActivity, HomeActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun showGif(view: View) {
        val imageView: ImageView = binding.imgUser
        Glide.with(this).load(R.drawable.ic_user_splash).into(imageView)
    }
}