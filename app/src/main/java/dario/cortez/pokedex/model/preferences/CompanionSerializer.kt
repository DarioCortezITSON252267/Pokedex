package dario.cortez.pokedex.model.preferences

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import dario.cortez.pokedex.model.domain.Companion
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream

object CompanionSerializer : Serializer<Companion> {
    override val defaultValue: Companion = Companion(number = -1, selectedAt = 0L)

    override suspend fun readFrom(input: InputStream): Companion {
        return try {
            Json.decodeFromString(Companion.serializer(), input.readBytes().decodeToString())
        } catch (exception: SerializationException) {
            throw CorruptionException("No se pudo leer el JSON", exception)
        }
    }

    override suspend fun writeTo(t: Companion, output: OutputStream) {
        output.write(Json.encodeToString(Companion.serializer(), t).encodeToByteArray())
    }
}
