package com.ouday.cryptowalletsample.common

import android.content.Context
import coil.ImageLoader
import coil.decode.SvgDecoder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object ImageLoaderModule {

    @Provides
    @Singleton
    fun provideImageLoader(context: Context) = ImageLoader.Builder(context)
        .components {
            add(SvgDecoder.Factory())
        }
        .build()
}

fun getImageUrl(image: String) = "http://192.168.178.242:777/images/$image"