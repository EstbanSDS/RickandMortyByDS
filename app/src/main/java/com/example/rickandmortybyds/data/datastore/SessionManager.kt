package com.example.rickandmortybyds.data.datastore

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

private const val DATASTORE_NAME = "session_preferences" // nombra al archivo donde se guardaran los datos

private val Context.dataStore by preferencesDataStore(name = DATASTORE_NAME) // se agrega una propiedad nueva al context (cada vez que se tenga un context tambien se tendra un DataStore "session_preferences"

class SessionManager(
    private val context: Context  // para poder trabajar se necesita un context que se usara para toda la clase
) {

    companion object {  // objetos que pertenecen a la clase (se crean llaves)

        private val USER_EMAIL =
            stringPreferencesKey("user_email")

        private val USER_NAME =
            stringPreferencesKey("user_name")

        private val USER_ROLE =
            stringPreferencesKey("user_role")

        private val IS_LOGGED_IN =      //bandera de sesión activa
            booleanPreferencesKey("is_logged_in")
    }

    suspend fun saveUser(       // funcion para guardar
        email: String,
        name: String,
        role: String
    ) {
        context.dataStore.edit { preferences ->     // indica que se modificara informacion

            preferences[USER_EMAIL] = email     // se asignan a los objetos
            preferences[USER_NAME] = name
            preferences[USER_ROLE] = role


            preferences[IS_LOGGED_IN] = true
        }
    }

    suspend fun isLoggedInOnce(): Boolean {     //leer sesión una vez
        return isLoggedIn.first()
    }


    val userEmail: Flow<String> =       // aqui solo se leera la informacion ( ve al datastore, busca la llave, "user_email" y devuelve el resultado
        context.dataStore.data.map { preferences ->
            preferences[USER_EMAIL] ?: ""
        }

    val userName: Flow<String> =
        context.dataStore.data.map { preferences ->
            preferences[USER_NAME] ?: ""
        }

    val userRole: Flow<String> =
        context.dataStore.data.map { preferences ->
            preferences[USER_ROLE] ?: ""
        }

    // 🔥 NUEVO: estado real de login
    val isLoggedIn: Flow<Boolean> =
        context.dataStore.data.map { preferences ->
            preferences[IS_LOGGED_IN] ?: false
        }

    suspend fun clearSession() {        // cuando se presione clearSession se eliminan todas las llaves
        context.dataStore.edit { preferences ->

            preferences.clear()

            // opcional redundante (pero seguro)
            preferences[IS_LOGGED_IN] = false
        }
    }
}

