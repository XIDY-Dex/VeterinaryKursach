package tkuik.alexkarav.veterinary.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStoreManager(val context: Context) {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "app_settings")

    suspend fun insertIntroScreenCompletion(completed: Boolean) {
        context.dataStore.edit { settings ->
            settings[introCompletedKey] = completed
        }
    }

    fun getIntroScreenCompletion(): Flow<Boolean> {
        return context.dataStore.data.map { settings ->
            settings[introCompletedKey] ?: false
        }
    }


    companion object {
        val introCompletedKey = booleanPreferencesKey("INTRO_COMPLETED")
    }
}