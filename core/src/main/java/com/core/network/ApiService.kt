package com.core.network

import com.google.gson.JsonElement
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("top/anime")
    suspend fun fetchTopAnime(): Response<JsonElement>

    @GET("anime/{id}")
    suspend fun getAnimeDetail(@Path("id") id: Int): Response<JsonElement>
}