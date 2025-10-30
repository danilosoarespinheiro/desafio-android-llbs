package com.example.desafioandroid

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Defines the API endpoints for interacting with the Trello service using Retrofit.
 */
interface TrelloService {

    @GET("boards")
    fun getBoards(
        @Query("key") key: String,
        @Query("token") token: String
    ): Call<Boards>
}