package com.ranosan.challenge.boof.core.util

import com.ranosan.challenge.boof.core.BuildConfig

object Constants {
    private const val BEARER = "Bearer "
    const val AUTHORIZATION = "Authorization"
    private const val BASE_URL_IMAGE = BuildConfig.BASE_URL_IMAGE

    fun getBearer(): String {
        return BEARER + BuildConfig.API_KEY
    }

    fun getImageUrl(imageUrl: String?): String {
        return BASE_URL_IMAGE + imageUrl
    }
}