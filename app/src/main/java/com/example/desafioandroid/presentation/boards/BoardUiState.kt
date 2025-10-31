package com.example.desafioandroid.presentation.boards

import com.example.desafioandroid.domain.model.Board

/**
 * Represents the different states for the Board screen UI.
 */
sealed class BoardUiState {
    /** The UI is in a loading state, typically while fetching data. */
    object Loading : BoardUiState()
    /** The data has been successfully loaded and is ready to be displayed. */
    data class Success(val boards: List<Board>) : BoardUiState()
    /** An error occurred while trying to load the data. */
    data class Error(val message: String) : BoardUiState()
}