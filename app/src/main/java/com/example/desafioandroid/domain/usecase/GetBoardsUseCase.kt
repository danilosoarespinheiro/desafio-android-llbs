package com.example.desafioandroid.domain.usecase

import com.example.desafioandroid.data.repository.BoardsRepository
import com.example.desafioandroid.domain.model.Board
import javax.inject.Inject

/**
 * Use case for retrieving a list of all available boards.
 *
 * @param repository The repository responsible for fetching board data.
 */
class GetBoardsUseCase @Inject constructor(
    private val repository: BoardsRepository
) {
    suspend operator fun invoke(): Result<List<Board>> {
        return repository.getBoards()
    }
}