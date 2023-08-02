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
    private val FIRSTACCESS = booleanPreferencesKey("FIRSTACCESS")

    suspend fun getFirstAccess(): Boolean {
        val accessValue: Flow<Boolean> = context.dataStore.data
            .map { preferences ->
                preferences[FIRSTACCESS] ?: false
            }
        return accessValue.first()
    }

    suspend fun setFirstAccess() {
        context.dataStore.edit { preferences ->
            preferences[FIRSTACCESS] = true
        }
    }
}