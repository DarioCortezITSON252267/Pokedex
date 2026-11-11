package dario.cortez.pokedex.model.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import dario.cortez.pokedex.model.domain.Companion
import dario.cortez.pokedex.model.preferences.CompanionSerializer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow

val Context.datastore: DataStore<Companion> by dataStore(
    fileName = "companion.json",
    serializer = CompanionSerializer,
    scope = CoroutineScope(Dispatchers.IO)
)

class CompanionDataStore(private val context: Context) {
    suspend fun saveCompanion(number: Int) {
        context.datastore.updateData {
            Companion(number = number, selectedAt = System.currentTimeMillis())
        }
    }

    fun companion(): Flow<Companion> = context.datastore.data
}
