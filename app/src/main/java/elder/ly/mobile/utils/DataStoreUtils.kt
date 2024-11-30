package elder.ly.mobile.utils

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import elder.ly.mobile.domain.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore by preferencesDataStore(name = "user_data")
val userData = stringPreferencesKey("user_data")

suspend fun saveUser(context: Context, user: User) {
    val gson = Gson()
    val userJson = gson.toJson(user)

    context.dataStore.edit { preferences ->
        preferences[userData] = userJson
    }
}

class UserNotFoundException(message: String) : Exception(message)

fun getUser(context: Context): Flow<User> {
    val gson = Gson()
    return context.dataStore.data.map { preferences ->
        val userJson = preferences[userData] ?: throw UserNotFoundException("Não há usuário na aplicação")
        gson.fromJson(userJson, User::class.java)
    }
}

suspend fun clearUser(context: Context) {
    context.dataStore.edit { preferences ->
        preferences.clear()
    }
}