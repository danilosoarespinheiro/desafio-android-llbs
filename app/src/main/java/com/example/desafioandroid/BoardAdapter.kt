package com.example.desafioandroid

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class BoardAdapter(
    private val boards: Boards
) : RecyclerView.Adapter<BoardItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BoardItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_board_item, parent, false)

        return BoardItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: BoardItemViewHolder, position: Int) {
        holder.bind(boards[position])
    }

    override fun getItemCount(): Int = boards.size
}
