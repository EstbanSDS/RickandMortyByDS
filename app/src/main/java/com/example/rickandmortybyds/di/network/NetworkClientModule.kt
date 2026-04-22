package com.example.rickandmortybyds.di.network

import android.os.StrictMode
import com.example.rickandmortybyds.utils.application.Constants.CONNECT_TIMEOUT
import com.example.rickandmortybyds.utils.application.Constants.READ_TIMEOUT
import com.example.rickandmortybyds.utils.application.Constants.RICK_AND_MORTY_API
import com.example.rickandmortybyds.core.network.ApiServiceRickAndMorty
import com.example.rickandmortybyds.utils.getDateHeader
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Headers
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import okio.Buffer
import org.json.JSONArray
import org.json.JSONObject
import javax.inject.Named
import javax.inject.Singleton
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
object NetworkClientModule {
    private val APPLICATION_JSON = "application/json"

    @Provides
    @Singleton
    @Named("ApiRickAndMorty")
    fun providerBaseUrlRickAndMorty(): String = RICK_AND_MORTY_API

    @Provides
    @Named("OkHttpClient")
    fun provideOkHttpClientServer(): OkHttpClient {
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        val logInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                val original = chain.request()
                val request = original.newBuilder()
                    .header("Content-Type", APPLICATION_JSON)
                    .header("fecha-peticion", String().getDateHeader())
                    .method(original.method, original.body)
                    .build()
                val headers: Headers = request.headers

                bodyToString(request)?.let {
                    val headString: StringBuilder =
                        java.lang.StringBuilder(".\n ================ HEADERS ================\n")
                    headers.forEach { head ->
                        headString.append("\n")
                        headString.append(head.first)
                        headString.append(" : ")
                        headString.append(head.second)
                        headString.append("\n")
                    }
                    headString.append("\n")
                    headString.append("Request")
                    headString.append("\n")
                    headString.append(request.url)
                    headString.append("\n")
                    headString.append("Body: ")
                    headString.append("\n")
                    headString.append(it)
                    headString.append("\n")

                    Timber.e(". $headString ")
                }
                chain.proceed(request)
            }
            .addInterceptor { chain ->
                val original = chain.request()
                val request = original.newBuilder()
                chain.proceed(request.build())
            }
            .addInterceptor(logInterceptor)
            .addInterceptor(getPrettyLoggingInterceptor())
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .build()
    }

    fun bodyToString(request: Request): String? {
        return try {
            val copy = request.newBuilder().build()
            val buffer = Buffer()
            copy.body!!.writeTo(buffer)
            buffer.readUtf8()
        } catch (e: Exception) {
            null
        }
    }

    fun getPrettyLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor { message ->
            try {
                val prettyMessage = when {
                    message.trim().startsWith("{") -> JSONObject(message).toString(4)
                    message.trim().startsWith("[") -> JSONArray(message).toString(4)
                    else -> message
                }
                println("🌐 $prettyMessage")
            } catch (e: Exception) {
                println("🌐 $message")
            }
        }
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    @Singleton
    @Provides
    @Named("RetrofitInstanceRickAndMorty")
    fun providesBaseURLRickANdMorty(
        @Named("ApiRickAndMorty") baseUrl: String,
        @Named("OkHttpClient") okHttpClient: OkHttpClient,
    ): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder().setPrettyPrinting().create()
                )
            )
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .build()
    }

    @Singleton
    @Provides
    fun restServiceRickAndMorty(@Named("RetrofitInstanceRickAndMorty") retrofit: Retrofit): ApiServiceRickAndMorty {
        return retrofit.create(ApiServiceRickAndMorty::class.java)
    }
}