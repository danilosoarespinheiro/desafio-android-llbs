package com.example.desafioandroid.presentation.boards

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.desafioandroid.databinding.FragmentBoardsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */

@AndroidEntryPoint
class BoardsFragment : Fragment() {

    private var _binding: FragmentBoardsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: BoardsViewModel by viewModels()
    private lateinit var boardAdapter: BoardAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBoardsBinding.inflate(inflater, container, false)
        setupRecyclerView()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeUiState()
    }

    private fun setupRecyclerView() {
        boardAdapter = BoardAdapter(emptyList())
        binding.recyclerView.apply {
            adapter = boardAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun observeUiState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.uiState.collect { state ->
                binding.progressBar.isVisible = state is BoardUiState.Loading

                when (state) {
                    is BoardUiState.Success -> {
                        boardAdapter.updateData(state.boards)
                    }
                    is BoardUiState.Error -> {
                        Toast.makeText(requireContext(), state.message, Toast.LENGTH_SHORT).show()
                    }
                    is BoardUiState.Loading -> {
                        // Handled by the isVisible binding above
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
