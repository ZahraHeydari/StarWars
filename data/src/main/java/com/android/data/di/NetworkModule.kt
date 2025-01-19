package com.android.data.di

import com.android.data.SwapiDataService
import com.android.data.repository.SwapiRepositoryImp
import com.android.domain.repository.SwapiRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val BASE_URL = "https://swapi.dev"
private const val TIME_OUT = 60L

val networkModule = module {
    single { createCharactersRepository(get()) }
    single { createService(get()) }
    single { createOkHttpClient() }
    single { Gson().newBuilder().create() }
    single { createRetrofit(get()) }
}

private fun createCharactersRepository(dataService: SwapiDataService): SwapiRepository {
    return SwapiRepositoryImp(dataService)
}

private fun createService(retrofit: Retrofit) = retrofit.create(SwapiDataService::class.java)

private fun createOkHttpClient(): OkHttpClient {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    return OkHttpClient.Builder()
        .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
        .writeTimeout(TIME_OUT, TimeUnit.SECONDS)
        .readTimeout(TIME_OUT, TimeUnit.SECONDS)
        .retryOnConnectionFailure(true)
        .addInterceptor(httpLoggingInterceptor)
        .addNetworkInterceptor { chain ->
            chain.proceed(chain.request().newBuilder().build())
        }
        .build()
}

private fun createRetrofit(okHttpClient: OkHttpClient) = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .client(okHttpClient)
    .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
    .build()