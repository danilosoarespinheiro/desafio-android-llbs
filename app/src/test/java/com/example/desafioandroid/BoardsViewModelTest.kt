package com.example.desafioandroid

import app.cash.turbine.test
import com.example.desafioandroid.domain.model.Board
import com.example.desafioandroid.domain.usecase.GetBoardsUseCase
import com.example.desafioandroid.presentation.boards.BoardUiState
import com.example.desafioandroid.presentation.boards.BoardsViewModel
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock

/**
 * Test class for [BoardsViewModel].
 *
 * This class tests the various states of the UI (`BoardUiState`) as emitted by the `BoardsViewModel`
 * based on the results from the `GetBoardsUseCase`.
 */
@OptIn(ExperimentalCoroutinesApi::class)
class BoardsViewModelTest {

    private val getBoardsUseCase: GetBoardsUseCase = mock()

    private lateinit var viewModel: BoardsViewModel

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun whenFetchBoardsIsSuccessfulUiStateShouldEmitSuccess() = runTest {

        val boards = listOf(Board(
            id = "1", name = "Test Board", desc = "Test desc", closed = false
        ))
        whenever(getBoardsUseCase.invoke()).thenReturn(Result.success(boards))

        viewModel = BoardsViewModel(getBoardsUseCase)

        viewModel.uiState.test {

            assertEquals(BoardUiState.Loading, awaitItem())

            val successState = awaitItem()
            assertTrue(successState is BoardUiState.Success)
            assertEquals(boards, (successState as BoardUiState.Success).boards)

            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun whenFetchBoardsFailsUiStateShouldEmitError() = runTest {

        val errorMessage = "Network Error"
        whenever(getBoardsUseCase.invoke()).thenReturn(Result.failure(RuntimeException(errorMessage)))

        viewModel = BoardsViewModel(getBoardsUseCase)

        viewModel.uiState.test {
            assertEquals(BoardUiState.Loading, awaitItem())

            val errorState = awaitItem()
            assertTrue(errorState is BoardUiState.Error)
            assertEquals(errorMessage, (errorState as BoardUiState.Error).message)

            cancelAndIgnoreRemainingEvents()
        }
    }
}
