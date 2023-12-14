package com.ouday.cryptowalletsample.common

import android.content.Context
import android.graphics.drawable.Drawable
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult

suspend fun loadImage(context: Context, imageUrl: String): Drawable? {
    val imageLoader = ImageLoader(context)
    val request = ImageRequest.Builder(context)
        .data(imageUrl)
        .build()

    val result = imageLoader.execute(request)
    return if (result is SuccessResult) result.drawable else null
}