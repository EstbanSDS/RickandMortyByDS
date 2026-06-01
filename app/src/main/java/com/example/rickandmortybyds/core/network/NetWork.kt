package com.example.rickandmortybyds.core.network

import android.Manifest
import android.content.Context
import android.net.ConnectivityManager
import androidx.annotation.RequiresPermission
import com.example.rickandmortybyds.R
import com.example.rickandmortybyds.core.ConnectivityObserver
import com.example.rickandmortybyds.utils.application.ApiServiceState
import com.example.rickandmortybyds.utils.application.ErrorApiResponse
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import java.net.HttpURLConnection
import java.net.SocketTimeoutException
import java.net.URL
import javax.inject.Inject

class NetWork @Inject constructor(@ApplicationContext val context: Context) {

    suspend inline fun <T> executeEndPoint(
        crossinline callFunctions: suspend () -> T,
    ): Flow<ApiServiceState<T>> {
        val result = isNetworkAvailable()
        if (result) {
            return flow {
                emit(ApiServiceState.Loading)
                val data = callFunctions.invoke()
                emit(ApiServiceState.Success(data))
            }.handleErrors()
        } else {
            return flow {
                emit(ApiServiceState.Loading)
                emit(
                    ApiServiceState.Error(
                        message = ErrorApiResponse(
                            ramCode = 4003,
                            descripcion = context.getString(R.string.msg_without_network)
                        )
                    )
                )
            }
        }
    }

    suspend inline fun <T> executeRoomQuery(
        crossinline dbQuery: suspend () -> T,
    ): Flow<ApiServiceState<T>> {
        return flow {
            emit(ApiServiceState.Loading)
            val data = dbQuery.invoke()
            emit(ApiServiceState.Success(data))
        }.catch { exception ->
            // Maneja errores específicos de la base de datos aquí
            emit(
                ApiServiceState.Error(
                    message = ErrorApiResponse(
                        ramCode = 5001, // Código personalizado para errores locales
                        descripcion = exception.localizedMessage ?: "Error en la base de datos"
                    )
                )
            )
        }
    }

    fun <T> Flow<ApiServiceState<T>>.handleErrors(): Flow<ApiServiceState<T>> =
        catch { exception ->
            exception.printStackTrace()

            when (exception) {
                is HttpException -> {
                    emit(
                        ApiServiceState.Error(
                            message = ErrorApiResponse(
                                ramCode = exception.code(),
                                descripcion = validErrorException(exception)
                            )
                        )
                    )
                }

                is IOException -> {
                    emit(
                        ApiServiceState.Error(
                            message = ErrorApiResponse(
                                ramCode = 4000,
                                descripcion = validErrorException(exception)
                            )
                        )
                    )
                }

                is SocketTimeoutException -> {
                    emit(
                        ApiServiceState.Error(
                            message = ErrorApiResponse(
                                ramCode = 4001,
                                descripcion = validErrorException(exception)
                            )
                        )
                    )
                }

                is Exception -> {
                    emit(
                        ApiServiceState.Error(
                            message = ErrorApiResponse(
                                ramCode = 4001,
                                descripcion = validErrorException(exception)
                            )
                        )
                    )
                }

                else -> {
                    emit(
                        ApiServiceState.Error(
                            message = ErrorApiResponse(
                                ramCode = 4002,
                                descripcion = exception.message
                            )
                        )
                    )
                }
            }
        }

    private fun validErrorException(exception: Exception): String {
        var response = exception.message

        exception.message?.let {
            if (it.contains("Java.lang.illegalStateException")) {
                response = "Ha ocurrido un error, por favor intente nuevamente. 500"
            }
        }
        return response ?: "Error desconocido"
    }

    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    @JvmName("isNetworkAvailable1")
    fun isNetworkAvailable(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        var state = true
        val networkInfo = connectivityManager.activeNetworkInfo
        val urlConnection =
            URL("https://clients3.google.com/generate_204").openConnection() as HttpURLConnection
        try {
            urlConnection.setRequestProperty("User-Agent", "Android")
            urlConnection.setRequestProperty("Connection", "close")
            urlConnection.connectTimeout = 1500
            urlConnection.readTimeout = 1500
            urlConnection.connect()
            if (urlConnection.responseCode == 204) {
                state = true
            } else {
                state = false
            }

        } catch (e: IOException) {
            state = false
            urlConnection.disconnect()
            ConnectivityObserver.Status.SinAccesoAInternet
        } finally {
            urlConnection.disconnect()
        }
        return networkInfo?.isConnected == state
    }
}