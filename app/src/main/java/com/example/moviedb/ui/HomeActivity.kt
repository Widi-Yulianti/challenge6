package com.example.moviedb.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.datastore.preferences.core.booleanPreferencesKey
import com.example.moviedb.R
import com.example.moviedb.databinding.ActivityHomeBinding
import kotlinx.coroutines.runBlocking

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val actionBar = supportActionBar
        actionBar?.title = ""

        listener()
    }

    private fun listener() {
        binding.toolbar.setOnMenuItemClickListener { item ->
            when(item.itemId){
                R.id.itemLogOut -> {
                    val intent = Intent(this@HomeActivity, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                    true
                }
                else -> false
            }
        }
    }

    private fun userLogOut() {
        runBlocking {
            DataStoreManager.setDataStore(preferencesKey = booleanPreferencesKey("HAS_LOGIN"), false)
            val intent = Intent(this@HomeActivity, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
}