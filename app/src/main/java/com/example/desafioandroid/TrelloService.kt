package com.example.desafioandroid

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TrelloService {

    @GET("boards")
    fun getBoards(
        @Query("key") key: String,
        @Query("token") token: String
    ): Call<Boards>
}