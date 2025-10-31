package com.example.desafioandroid.presentation.boards

import android.graphics.Color
import androidx.recyclerview.widget.RecyclerView
import com.example.desafioandroid.domain.model.Board
import com.example.desafioandroid.databinding.ListBoardItemBinding

/**
 * [RecyclerView.ViewHolder] for displaying a single [Board].
 *
 * @property binding The view binding for the board item layout.
 */
class BoardItemViewHolder(
    private val binding: ListBoardItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    /** Binds a [Board] to the view, setting the position, name, and background color. */
    fun bind(boardItem: Board) {
        val position = bindingAdapterPosition + 1
        binding.boardPosition.text = "$position"
        binding.boardName.text = boardItem.name

        if (boardItem.closed) {
            binding.cardView.setCardBackgroundColor(Color.LTGRAY)
        } else {
            binding.cardView.setCardBackgroundColor(Color.WHITE)
        }
    }
}
