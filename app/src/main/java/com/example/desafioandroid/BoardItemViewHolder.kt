package com.example.desafioandroid

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_board_item.view.*

class BoardItemViewHolder(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {

    fun bind(boardItem: BoardItem) {
        val position = adapterPosition + 1
        itemView.boardPosition.text = "$position"
        itemView.boardName.text = boardItem.name
    }
}
