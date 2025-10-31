package com.example.desafioandroid.data.repository

import com.example.desafioandroid.domain.model.Board

/**
 * Interface for data operations related to social media boards.
 * This acts as a contract for data sources (e.g., local, remote) to implement.
 */
interface BoardsRepository {
    /**
     * Fetches a list of boards.
     * @return A [Result] containing a list of [Board] on success, or an exception on failure.
     */
    suspend fun getBoards(): Result<List<Board>>
}