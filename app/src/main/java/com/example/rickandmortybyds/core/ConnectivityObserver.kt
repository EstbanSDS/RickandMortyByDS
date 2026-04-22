package com.example.rickandmortybyds.core

import kotlinx.coroutines.flow.Flow

interface ConnectivityObserver {
    fun observe(): Flow<Status>

    enum class Status(val message: String) {
        Conectado("Conectado"),
        Desconectado("Desconectado"),
        SinAccesoAInternet("Conectado, pero sin acceso a Internet");

        override fun toString(): String {
            return message
        }
    }
}