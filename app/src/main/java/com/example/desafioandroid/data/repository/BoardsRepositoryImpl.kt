package com.example.desafioandroid.data.repository

import com.example.desafioandroid.BuildConfig
import com.example.desafioandroid.data.api.TrelloService
import com.example.desafioandroid.domain.model.Board
import javax.inject.Inject

/**
 * Implementation of the [BoardsRepository] interface.
 * This class is responsible for fetching board data from the Trello API.
 *
 * @param apiService The Retrofit service for interacting with the Trello API.
 */
class BoardsRepositoryImpl @Inject constructor(
    private val apiService: TrelloService
) : BoardsRepository {

    override suspend fun getBoards(): Result<List<Board>> {
        return try {
            val boardsDto = apiService.getBoards(
                key = BuildConfig.API_KEY,
                token = BuildConfig.API_TOKEN
            )
            // Map DTOs to Domain models
            val boards = boardsDto.map { Board(id = it.id, name = it.name, desc = it.desc, closed = it.closed) }
            Result.success(boards)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}