package com.example.desafioandroid

class Boards : ArrayList<BoardItem>()

data class BoardItem(
    val id: String,
    val name: String,
    val desc: String,
    val closed: Boolean
)
