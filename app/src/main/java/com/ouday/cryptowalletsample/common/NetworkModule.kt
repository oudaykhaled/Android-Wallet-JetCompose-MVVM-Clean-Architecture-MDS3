package com.ouday.cryptowalletsample.common

import com.ouday.cryptowalletsample.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        // Create a logging interceptor
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            // Set the logging level
            level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        }

        // Create an OkHttpClient and add the interceptor
        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        // Build the Retrofit instance
        return Retrofit.Builder()
            .baseUrl("http://192.168.178.242:777/")
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }
}
