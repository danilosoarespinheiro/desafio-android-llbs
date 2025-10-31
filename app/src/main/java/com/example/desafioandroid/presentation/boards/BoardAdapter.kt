package com.example.desafioandroid.presentation.boards

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.desafioandroid.databinding.ListBoardItemBinding
import com.example.desafioandroid.domain.model.Board

/**
 * A [RecyclerView.Adapter] that can display a list of [Board] items.
 * @property boards The list of [Board] objects to be displayed by the adapter.
 */
class BoardAdapter(
    private var boards: List<Board>
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

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newBoards: List<Board>) {
        this.boards = newBoards
        notifyDataSetChanged() // This tells the adapter to refresh the entire list.
    }
}
