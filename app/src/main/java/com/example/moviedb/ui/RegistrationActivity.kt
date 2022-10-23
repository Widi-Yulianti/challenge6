package com.example.moviedb.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.moviedb.databinding.ActivityRegistrationBinding
import kotlinx.coroutines.runBlocking

class RegistrationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistrationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        listener()
    }

    private fun listener() {
        binding.run {
            btnRegistration.setOnClickListener {
                if(etNameLogin.text.isNotEmpty() && etPassword.text.isNotEmpty() && etAddress.text.isNotEmpty() && etAge.text.isNotEmpty()){
                    saveUser()
                    val intent = Intent(this@RegistrationActivity, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this@RegistrationActivity, "Fill In All Fields", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun saveUser(){
        runBlocking {
            DataStoreManager.setDataStore(
                preferencesKey = stringPreferencesKey("NAME"),
                value = binding.etNameLogin.text.toString()
            )
            DataStoreManager.setDataStore(
                preferencesKey = stringPreferencesKey("PASSWORD"),
                value = binding.etPassword.text.toString()
            )
            DataStoreManager.setDataStore(
                preferencesKey = stringPreferencesKey("ADDRESS"),
                value = binding.etAddress.text.toString()
            )
            DataStoreManager.setDataStore(
                preferencesKey = intPreferencesKey("AGE"),
                value = binding.etAge.text.toString().toInt()
            )
            Toast.makeText(this@RegistrationActivity, "Succesfully Saved", Toast.LENGTH_SHORT).show()
        }
    }
}