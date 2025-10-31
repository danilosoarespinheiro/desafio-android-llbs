package com.example.desafioandroid.presentation.boards

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.desafioandroid.domain.usecase.GetBoardsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
/**
 * ViewModel for the boards screen.
 *
 * This ViewModel is responsible for fetching the list of boards and exposing the UI state
 * to be observed by the composable screen. It uses [GetBoardsUseCase] to retrieve the data.
 *
 * @property getBoardsUseCase The use case to fetch the list of boards.
 */
class BoardsViewModel @Inject constructor(
    private val getBoardsUseCase: GetBoardsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<BoardUiState>(BoardUiState.Loading)
    val uiState: StateFlow<BoardUiState> = _uiState

    init {
        fetchBoards()
    }

    private fun fetchBoards() {
        viewModelScope.launch {
            _uiState.value = BoardUiState.Loading
            getBoardsUseCase()
                .onSuccess { boards ->
                    _uiState.value = BoardUiState.Success(boards)
                }
                .onFailure { error ->
                    _uiState.value =
                        BoardUiState.Error(error.message ?: "An unknown error occurred")
                }
        }
    }
}