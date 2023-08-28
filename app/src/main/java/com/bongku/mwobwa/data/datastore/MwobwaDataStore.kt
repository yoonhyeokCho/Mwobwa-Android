package com.bongku.mwobwa.data.datastore

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MwobwaDataStore @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val Context.dataStore by preferencesDataStore(name = "dataStore")

    private val NETFLIX = booleanPreferencesKey("NETFLIX")
    private val DISNEY = booleanPreferencesKey("DISNEY")
    private val APPLETV = booleanPreferencesKey("APPLETV")

    suspend fun initializeOttCompanies() {
        context.dataStore.edit { preferences ->
            preferences[NETFLIX] = false
            preferences[DISNEY] = false
            preferences[APPLETV] = false
        }
    }

    suspend fun setOttCompany(contents: List<String>) {
        initializeOttCompanies()

        if (!contents.isEmpty()) {
            for (it in contents) {
                if (it == "NETFLIX") {
                    context.dataStore.edit { preferences ->
                        preferences[NETFLIX] = true
                    }
                } else if (it == "DISNEY") {
                    context.dataStore.edit { preferences ->
                        preferences[DISNEY] = true
                    }
                } else {
                    context.dataStore.edit { preferences ->
                        preferences[APPLETV] = true
                    }
                }
            }
        }
    }

    suspend fun getOttCompany(): List<String> {
        val contentsList = mutableListOf<String>()

        val netflixValue: Flow<Boolean> = context.dataStore.data
            .map { preferences ->
                preferences[NETFLIX] ?: false
            }
        val disneyValue: Flow<Boolean> = context.dataStore.data
            .map { preferences ->
                preferences[DISNEY] ?: false
            }
        val appleTvValue: Flow<Boolean> = context.dataStore.data
            .map { preferences ->
                preferences[APPLETV] ?: false
            }

        if (netflixValue.first()) {
            contentsList.add("netflix")
        }
        if (disneyValue.first()) {
            contentsList.add("disney")
        }
        if (appleTvValue.first()) {
            contentsList.add("appleTV")
        }
        return contentsList
    }

}