package com.mkrlabs.common.core.base.utils

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.doublePreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

@Singleton
class DataStoreManager @Inject constructor(@ApplicationContext private val appContext : Context) {

    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("APP_DATASTORE")
    }

    suspend fun saveBoolean(key: String, value: Boolean) {
        val prefKey = booleanPreferencesKey(key)
        appContext.dataStore.edit { data ->
            data[prefKey] = value
        }
    }

    suspend fun getBoolean(key: String, defaultValue: Boolean = false): Boolean {
        val prefKey = booleanPreferencesKey(key)
        val preferences = appContext.dataStore.data.first()
        return preferences[prefKey] ?: defaultValue
    }

    suspend fun saveString(key: String, value: String) {
        val prefKey = stringPreferencesKey(key)
        appContext.dataStore.edit { data ->
            data[prefKey] = value
        }
    }

    suspend fun getString(key: String, defaultValue: String = ""): String {
        val prefKey = stringPreferencesKey(key)
        val preferences = appContext.dataStore.data.first()
        return preferences[prefKey] ?: defaultValue
    }

    suspend fun saveDouble(key: String, value: Double) {
        val prefKey = doublePreferencesKey(key)
        appContext.dataStore.edit { data ->
            data[prefKey] = value
        }
    }

    suspend fun getDouble(key: String, defaultValue: Double = 0.0): Double {
        val prefKey = doublePreferencesKey(key)
        val preferences = appContext.dataStore.data.first()
        return preferences[prefKey] ?: defaultValue
    }

    suspend fun saveLong(key: String, value: Long) {
        val prefKey = longPreferencesKey(key)
        appContext.dataStore.edit { data ->
            data[prefKey] = value
        }
    }

    suspend fun getLong(key: String, defaultValue: Long = 0L): Long {
        val prefKey = longPreferencesKey(key)
        val preferences = appContext.dataStore.data.first()
        return preferences[prefKey] ?: defaultValue
    }

    suspend fun removeString(key: String) {
        appContext.dataStore.edit {
            val prefKey = stringPreferencesKey(key)
            if (it.contains(prefKey)) {
                it.remove(prefKey)
            }
        }
    }
}