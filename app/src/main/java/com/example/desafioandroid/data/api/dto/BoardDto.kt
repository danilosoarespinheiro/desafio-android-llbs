package com.example.desafioandroid.data.api.dto

/**
 * Data Transfer Object for a Trello board.
 *
 * @property id The unique identifier for the board.
 * @property name The name of the board.
 * @property desc The description of the board.
 * @property closed A boolean indicating whether the board is closed (archived).
 */
data class BoardDto(
    val id: String,
    val name: String,
    val desc: String,
    val closed: Boolean
)