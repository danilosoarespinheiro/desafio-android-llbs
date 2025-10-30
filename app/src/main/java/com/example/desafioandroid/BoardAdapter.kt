package com.example.desafioandroid

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.desafioandroid.databinding.ListBoardItemBinding

/**
 * A [RecyclerView.Adapter] that can display a list of [Boards] items.
 * @property boards The list of [Boards] objects to be displayed by the adapter.
 */
class BoardAdapter(
    private val boards: Boards
) : RecyclerView.Adapter<BoardItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BoardItemViewHolder {
        val binding = ListBoardItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return BoardItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BoardItemViewHolder, position: Int) {
        holder.bind(boards[position])
    }

    override fun getItemCount(): Int = boards.size
}
