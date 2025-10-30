package com.example.desafioandroid

import android.graphics.Color
import androidx.recyclerview.widget.RecyclerView
import com.example.desafioandroid.databinding.ListBoardItemBinding

/**
 * [RecyclerView.ViewHolder] for displaying a single [BoardItem].
 *
 * @property binding The view binding for the board item layout.
 */
class BoardItemViewHolder(
    private val binding: ListBoardItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    /** Binds a [BoardItem] to the view, setting the position, name, and background color. */
    fun bind(boardItem: BoardItem) {
        val position = adapterPosition + 1
        binding.boardPosition.text = "$position"
        binding.boardName.text = boardItem.name

        if (boardItem.closed) {
            binding.cardView.setCardBackgroundColor(Color.LTGRAY)
        } else {
            binding.cardView.setCardBackgroundColor(Color.WHITE)
        }
    }
}
