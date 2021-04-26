package com.example.simple_dictionary.core.di

import com.example.simple_dictionary.common.data.api.SkyengApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

private const val TIMEOUT_SECONDS = 30L

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    @Named("timeout-seconds")
    fun provideTimeoutSeconds(): Long = TIMEOUT_SECONDS

    @Provides
    @Singleton
    fun provideSkyengApi(@Named("http-client-open") client: OkHttpClient): SkyengApi {
        return Retrofit.Builder()
            .baseUrl("https://dictionary.skyeng.ru/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
            .create(SkyengApi::class.java)
    }


    @Provides
    @Singleton
    @Named("http-client-open")
    fun provideOpenHttpClient(
        @Named("timeout-seconds") timeoutSeconds: Long,
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(timeoutSeconds, TimeUnit.SECONDS)
            .build()
    }
}