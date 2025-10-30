package com.example.desafioandroid

class Boards : ArrayList<BoardItem>()

/**
 * Represents a single board item from the Trello API.
 *
 * @property id The unique identifier for the board.
 * @property name The name of the board.
 * @property desc The description of the board.
 * @property closed A boolean indicating whether the board is closed (archived).
 */
data class BoardItem(
    val id: String,
    val name: String,
    val desc: String,
    val closed: Boolean
)
