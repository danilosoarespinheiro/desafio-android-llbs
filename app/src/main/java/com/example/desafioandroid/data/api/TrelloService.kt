package com.example.desafioandroid.data.api

import com.example.desafioandroid.data.api.dto.BoardDto
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Defines the API endpoints for interacting with the Trello service using Retrofit.
 */
interface TrelloService {

    @GET("boards")
    suspend fun getBoards(
        @Query("key") key: String,
        @Query("token") token: String
    ): List<BoardDto>
}