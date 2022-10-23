package com.example.moviedb.ui

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

object DataStoreManager {

    private lateinit var application: Application

    fun init(application: Application){
        this.application = application
    }

    private val Context.preferencesDataStore: DataStore<Preferences> by preferencesDataStore(name = "USER_LOGIN")

    suspend fun <T: Any> setDataStore(
        preferencesKey: Preferences.Key<T>,
        value: T
    ){
        application.preferencesDataStore.edit { preferences ->
            preferences[preferencesKey] = value
        }
    }

    suspend fun <T: Any> readDataStore(
        preferencesKey: Preferences.Key<T>
    ) : T ? {
        return application.preferencesDataStore.data.first()[preferencesKey]
    }
}